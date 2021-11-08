## 0.0.1+1

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


## 0.0.1+2
(no changes)

## 0.0.2+3
(no changes)

## 0.0.2+4
(no changes)