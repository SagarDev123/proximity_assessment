package com.assessment.proximity_assessment.home

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assessment.proximity_assessment.MainActivity
import com.assessment.proximity_assessment.model.AirQualityList
import com.google.gson.Gson
import okhttp3.*
import okio.ByteString

class MainViewModel:ViewModel() {


    val airQualityLiveData = MutableLiveData<AirQualityList>()
    val airQualityError =MutableLiveData<String>()

   fun init(mainActivity: MainActivity) {
       val time = object :CountDownTimer(30*1000,2000){
           override fun onTick(p0: Long) {
              // println("Timer started")
               startServerActivity_()
           }

           override fun onFinish() {
             //  println("Timer completed")
           }

       }
       time.start()

    }

    private class EchoWebSocketListener(
        val airQualityLiveData: MutableLiveData<AirQualityList>,
        val airQualityError: MutableLiveData<String>
    ) : WebSocketListener() {
        private val NORMAL_CLOSURE_STATUS = 1000

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
//            webSocket.send("Hello, it's SSaurel !");
//            webSocket.send("What's up ?");
//            webSocket.send("deadbeef".decodeHex());
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("OnFailure message ---> ${t.message}")
            airQualityError.postValue(t.message)

        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
           // println("Receiving bytes : " + bytes.hex());
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
          //  println("Receiving bytes response : $text")
            val airQualityModel  = Gson().fromJson(text,AirQualityList::class.java)
            airQualityLiveData.postValue(airQualityModel)
          //  println("Receiving bytes response : $airQualityModel")
        }
    }




    private fun startServerActivity_() {
        val request = Request.Builder().url("ws://city-ws.herokuapp.com/").build()
        val okHttpClient = OkHttpClient()
        val listener = EchoWebSocketListener(airQualityLiveData,airQualityError)
        val ws = okHttpClient.newWebSocket(request,listener)
        okHttpClient.dispatcher.executorService.shutdown();
    }
}