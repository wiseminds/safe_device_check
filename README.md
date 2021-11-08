# safe_device_check (null-safety)

Check whether the device meets the following criteria-
- Root/Jail break
- Is an Emulator
- Developer mode (Android only)
- Is app installed on external storage (Android only)

## Usage

**1. Add import-**

    import "package:safe_device_check/device_check.dart"

**2. Check for the required criteria-**
For eg.-

    bool _isRooted = await DeviceCheck.isRooted; //true or false

## Features
| Attributes | Android | iOS |
| --| --|--|
| `isRooted` |✔ `true`/`false`|✔ `true`/`false`|
| `isEmulator` |✔ `true`/`false`|✔ `true`/`false`|
| `isDeveloperModeOn` |✔ `true`/`false`| always `false`|
| `isInstalledOnExternalStorage` |✔ `true`/`false`|always `false`|
