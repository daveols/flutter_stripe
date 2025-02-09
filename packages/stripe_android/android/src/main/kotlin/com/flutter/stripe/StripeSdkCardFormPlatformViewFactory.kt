package com.flutter.stripe

import android.content.Context
import com.reactnativestripesdk.CardFormViewManager
import com.reactnativestripesdk.StripeSdkModule
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class StripeSdkCardFormPlatformViewFactory(
        private val flutterPluginBinding: FlutterPlugin.FlutterPluginBinding,
        private val cardFormViewManager: CardFormViewManager,
        private val sdkAccessor: () -> StripeSdkModule
) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        val channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter.stripe/card_form_field/${viewId}")
        val creationParams = args as? Map<String?, Any?>?
        return StripeSdkCardFormPlatformView(context, channel, viewId, creationParams, cardFormViewManager, sdkAccessor)
    }
}
