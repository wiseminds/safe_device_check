import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

/// Check whether the device is safe,
/// Available checks,
/// * Root => [isRooted]
/// * Developer Mode => [isDeveloperModeEnabled]
/// * Emulator => [isEmulator]
/// * App installed on external storage => [isInstalledOnExternalStorage]
class DeviceCheck {
  static const MethodChannel _channel = const MethodChannel('device_check');

  /// Check the device platorm and version
  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  /// Check if the device is rooted/jailbroken
  static Future<bool> get isRooted async =>
      await _channel.invokeMethod("isRooted");

  /// Check if the device has developer mode enabled (only Android)
  static Future<bool> get isDeveloperModeEnabled async {
    if (Platform.isAndroid)
      return await _channel.invokeMethod("isDeveloperModeOn");
    return Future.value(false);
  }

  /// Check if the app is run on an emulator/simulator
  static Future<bool> get isEmulator async =>
      await _channel.invokeMethod("isEmulator");

  /// Check if the app is installed on an external storage (only Android)
  static Future<bool> get isInstalledOnExternalStorage async {
    if (Platform.isAndroid)
      return await _channel.invokeMethod("isInstalledOnExternalStorage");
    return Future.value(false);
  }
}
