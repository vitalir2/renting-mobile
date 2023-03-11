//
//  ToastModifier.swift
//  Renting
//
//  Created by Vitaly Khvostov on 11.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import Foundation
import SwiftUI

struct ToastModifier: ViewModifier {
    @Binding var show: Bool

    let toastView: ToastView

    func body(content: Content) -> some View {
        ZStack {
            content
            if show {
                toastView
            }
        }
    }
}

extension View {
    func toast(toastView: ToastView, show: Binding<Bool>) -> some View {
        self.modifier(ToastModifier.init(show: show, toastView: toastView))
    }
}
