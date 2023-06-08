import Flutter
import UIKit

public class SwiftDeviceCheckPlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "device_check", binaryMessenger: registrar.messenger())
        let instance = SwiftDeviceCheckPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }

    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        switch call.method {
        case "platformVersion":
            result("iOS " + UIDevice.current.systemVersion)
            return
        case "isRooted":
            result(DeviceCheck.isRooted())
            return
        case "isEmulator":
            result(DeviceCheck.isSimulator())
            return
        default:
            result(FlutterMethodNotImplemented)
            return
        }
        
    }
}
