//
//  SplashScreen.swift
//  Renting
//
//  Created by Vitaly Khvostov on 09.05.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

public let splashScreenTimeoutDefaultSeconds: TimeInterval = 1

// Taken from https://github.com/globulus/swiftui-splash-screen/tree/main
public struct SplashScreen<SplashContent: View>: ViewModifier {
    private let timeout: TimeInterval
    private let splashContent: () -> SplashContent
    
    @State private var isActive = true
    
    public init(
        timeout: TimeInterval = splashScreenTimeoutDefaultSeconds,
        @ViewBuilder splashContent: @escaping () -> SplashContent
    ) {
        self.timeout = timeout
        self.splashContent = splashContent
    }
    
    public func body(content: Content) -> some View {
        if isActive {
            splashContent()
                .onAppear {
                    DispatchQueue.main.asyncAfter(deadline: .now() + timeout) {
                        self.isActive = false
                    }
                }
        } else {
            content
        }
    }
}

public extension View {
    func splashScreen<SplashContent: View>(
        timeout: TimeInterval = splashScreenTimeoutDefaultSeconds,
        @ViewBuilder splashContent: @escaping () -> SplashContent
    ) -> some View {
        self.modifier(SplashScreen(timeout: timeout, splashContent: splashContent))
    }
}

struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        List(1..<6) { index in
          Text("Item \(index)")
        }.splashScreen {
            ZStack {
                Color.white
                Image("RentingLogoFull")
                    .resizable()
                    .frame(width: 100, height: 100)
            }
        }
    }
}
