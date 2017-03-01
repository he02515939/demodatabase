package com.example.timmy.demodatabae;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.view.View.X;

/**
 * Created by timmy on 2017/3/1.
 */

public class BackgroundTask extends AsyncTask <String,Void,String>
//AsyncTask<Params, Progress, Result>，這是基本的架構，使用泛型來定義參數，
// 泛型意思是，你可以定義任意的資料型態給他。
// Params ： 參數，你要餵什麼樣的參數給它。給 doinBackground
// Progress ： 進度條，進度條的資料型態要用哪種。給om progress update
// Result ： 結果，你希望這個背景任務最後會有什麼樣的結果回傳給你。onPostExecute
//AsyncTask會有四個步驟。
// onPreExecute ： 執行前，一些基本設定可以在這邊做。
// doInBackground ： 執行中，在背景做任務。
// onProgressUpdate ： 執行中，當你呼叫publishProgress的時候會到這邊，可以告知使用者進度。
// onPostExecute ： 執行後，最後的結果會在這邊。
//  if you want more detail :   http://aiur3908.blogspot.tw/2015/06/android-asynctask.html
{
    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx=ctx ;
    }
    protected String doInBackground(String... params) //背景中做的事
    {
        String reg_url="http://10.0.2.2/project/register.php";
        String method=params[0];
        if(method.equals("register")){
           String name=params[1];
           String password=params[2];
           String contact=params[3];
           String country=params[4];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                //　先取得HttpURLConnection urlConn = new URL("http://www.google.com").openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);//post的情況下需要設置DoOutput為true
                OutputStream os =httpURLConnection.getOutputStream();//java.io.OutputStream是以byte為單位的輸出串流（stream）類別，用來處理出的資料通道
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //InputStreamReader 與 OutputStreamWriter 類別屬於「由 byte 轉成 char」的
                // 中繼接頭。舉例來說，在處理資料輸入時，資料輸出的 outputStream 像是小口徑的水管
                // ，而我們希望能將小水管轉換為大口徑的 Bufferedwriter 這類大水管時，可利用 outputStreamWriter 這類轉換器，將 outStream 轉換為 Writer，
                //更多http解說:  https://litotom.com/2016/05/11/java%E7%9A%84%E7%B6%B2%E8%B7%AF%E7%A8%8B%E5%BC%8F%E8%A8%AD%E8%A8%88/
                String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                             URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                             URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                             URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode(country,"UTF-8");
                             //&在php中表示下一個表單欄位的開始
                 bufferedWriter.write(data);// //使用缓冲区中的方法将数据写入到缓冲区中。
                 bufferedWriter.flush();//flush();將緩衝數據寫到文件去
                 bufferedWriter.close();
                 os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return"Registration success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
       return null;
    }

    @Override
    protected void  onPostExecute(String result)//最終執行得結果
    {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        //Toast.makeText(context, text, duration).show();
        //duration這個參數，duration顧名思義就是Toast訊息顯示的持續時間，只有兩個整數數值有作用，
        // 分別是短時間的「Toast.LENGTH_SHORT」和長時間的「Toast.LENGTH_LONG」
    }

    @Override
    protected void  onPreExecute() //AsyncTask 執行時會 第一個被呼叫的
    {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values)//會以 Void 的型態回報進度
     {
        super.onProgressUpdate(values);
    }
}
