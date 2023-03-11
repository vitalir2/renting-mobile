//
//  ToastView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 11.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import Foundation
import SwiftUI

struct ToastView: View {

    let model: ToastData
    var onShowed: (() -> Void)?
    @Binding var isShowed: Bool

    @State private var delayTask: Task<Void, Error>?

    var body: some View {
        VStack {
            Spacer()
            HStack {
                Text(model.title)
            }
            .font(.headline)
            .padding(.vertical, 20)
            .padding(.horizontal, 40)
            .background(Color.backgroundSecondary)
            .clipShape(Capsule())
        }
        .frame(width: UIScreen.main.bounds.width / 1.25)
        .transition(AnyTransition.move(edge: .bottom).combined(with: .opacity))
        .onTapGesture {
            withAnimation {
                self.isShowed = false
            }
        }.onAppear(perform: {
            onShowed?()
            self.delayTask = Task {
                let nanos = UInt64(model.delaySeconds) * NSEC_PER_SEC
                try await Task.sleep(nanoseconds: nanos)
                withAnimation {
                    self.isShowed = false
                }
            }
        })
        .onDisappear(perform: {
            if let task = self.delayTask, !task.isCancelled {
                task.cancel()
                self.delayTask = nil
            }
        })

    }
}

struct ToastView_Previews: PreviewProvider {
    static var previews: some View {
        ToastView(
            model: ToastData(title: "Something went wrong", delaySeconds: 1),
            isShowed: .constant(true)
        )
    }
}
