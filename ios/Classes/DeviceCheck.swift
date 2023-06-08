//
//  DeviceCheck.swift
//  DeviceCheckPlugin
//
//  Created by Raj Yadav on 15/08/21.
//


public class DeviceCheck {

    static func isJailBroken() -> Bool {
        return method1() || method2()
    }

    static func isSimulator() -> Bool {
        #if targetEnvironment(simulator)
            return true;
        #else
            return false;
        #endif
    }

    static func isRooted() -> Bool {
        return isJailBroken() || isSimulator()
    }


    static private func method1() -> Bool {
        let fileManager = FileManager.default

        if isSimulator() {
            if fileManager.fileExists(atPath: "/Applications/Cydia.app")
                       || fileManager.fileExists(atPath: "/Library/MobileSubstrate/MobileSubstrate.dylib")
                       || fileManager.fileExists(atPath: "/bin/bash")
                       || fileManager.fileExists(atPath: "/usr/sbin/sshd")
                       || fileManager.fileExists(atPath: "/usr/bin/ssh")
                       || fileManager.fileExists(atPath: "/etc/apt")
                       || fileManager.fileExists(atPath: "/private/var/lib/apt/")
                       || UIApplication.shared.canOpenURL(URL(string: "cydia://package/com.example.package")!) {
                return true
            }
        }
        return false;
    }

    static private func method2() -> Bool {
        let fileManager = FileManager.default

        do {
             try ".".write(toFile: "/private/JailbreakTest.txt", atomically: true, encoding: String.Encoding.utf8)
            do{
                try fileManager.removeItem(atPath: "/private/JailbreakTest.txt")
            }catch{
                //ignored
            }
            return true
        } catch {
            //ignored
        }
        return false
    }
}
