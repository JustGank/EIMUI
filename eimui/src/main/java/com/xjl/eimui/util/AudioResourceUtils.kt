package com.xjl.eimui.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.app.ActivityCompat

object AudioResourceUtils {
    fun validateMicAvailability(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        } else {
            var available = true
            var recorder = AudioRecord(
                MediaRecorder.AudioSource.MIC, 44100,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_DEFAULT, 44100
            )
            try {
                if (recorder.recordingState != AudioRecord.RECORDSTATE_STOPPED) {
                    available = false
                }
                recorder.startRecording()
                if (recorder.recordingState != AudioRecord.RECORDSTATE_RECORDING) {
                    recorder.stop()
                    available = false
                }
                recorder.stop()
            } finally {
                recorder.release()
            }
            return available
        }


    }
}
