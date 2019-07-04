package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.email
import org.jetbrains.anko.share

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        webView.loadUrl("http://www.google.com")

        /*에디트텍스트가 선택되고 글자가 입력될 때마다 호출된다.
        인자로는 반응한 뷰, 액션 ID, 이벤트 세 가지가 있다.
        여깃는 뷰와 이벤트를 사용하지 않기 때문에 _로 대치될 수 있다
        actionId값은 Editorinfo 클래스에 상수로 정의된 값 중에서
        검색 버튼에 해당하는 상수와 비교하여 검색 버튼이 눌렸는지 비교*/

        urlEditView.setOnEditorActionListener {_, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                webView.loadUrl("http://" + urlEditView.text.toString())
                true
            }else{
                false
            }
        }

        //컨텍스트 메뉴 등록 webView를 길게 누르면 메뉴가 context된다.
        registerForContextMenu(webView)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.action_Share -> {
                //페이지 공유
                share(webView.url)
                return true
            }
            R.id.action_Browser -> {
                //기본 웹 브라우저에서 열기

                browse(webView.url)
                return true
            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){ webView.goBack() }
        else{ super.onBackPressed() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)//
        return true //액티비티에 메뉴가 있다고 인식
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_Google, R.id.Home -> { webView.loadUrl("http://www.google.com"); return true }
            R.id.action_Naver -> { webView.loadUrl("Http://www.naver.com"); return true}
            R.id.action_Acmicpc -> { webView.loadUrl("http://acmicpc.net"); return true}
            R.id.action_Call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:010-2591-7152")
                if(intent.resolveActivity(packageManager) != null) { startActivity(intent) }
                return true
            }
            R.id.action_MMS ->{
               // sendSMS("010-2591-7152", webView.url)
                //anko 라이브러리 사용시 간편하게 할 수 있다.

                //val smsManager = SmsManager.getDefault() as SmsManager
                //smsManager.sendTextMessage("010-8664-7152", null, "sms message", null,null)

               sendSMS()

                return true
            }
            R.id.action_Email -> {
                //email("test@example.com", "좋은 사이트", webView.url)

                //email 받는주소 제목 내용
                email("ieehyeon@naver.com", "좋은 사이트입니다", webView.url)
                return  true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
        //inflate 메서드를 사용하여 메뉴 리소스를 액티비티 컨텍스트 메뉴로서 사용하도록 한다.
        //이 코드는 옵션 메뉴와 같다
    }

   /* fun sendSMS(phoneNumber : String?, message:String?){
        var Sent :String? = "SMS_SNET"
        var Delivered = "SMS_DELIVERED"

    }*/

   public fun sendSMS(){
       /*val intent = Intent(Intent.ACTION_DIAL)
       intent.data = Uri.parse("tel:010-2591-7152")
       if(intent.resolveActivity(packageManager) != null) { startActivity(intent) }*/

       val uri = Uri.parse("smsto:010-8664-7152")
       val intent = Intent(Intent.ACTION_SENDTO, uri)
       intent.putExtra("sms_body", "the my sms text")
       startActivity(intent)

   }
}
