//
//  SecureInput.swift
//  Renting
//
//  Created by Vitaly Khvostov on 13.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct SecureInput: View {
    @Binding private var text: String
    @State private var isSecured: Bool = true
    @FocusState private var isFocused: Bool
    private var title: String

    init(_ title: String, text: Binding<String>) {
        self.title = title
        self._text = text
    }

    var body: some View {
        ZStack(alignment: .trailing) {
            Group {
                if isSecured {
                    SecureField(title, text: $text)
                        .focused($isFocused)
                } else {
                    TextField(title, text: $text)
                        .focused($isFocused)
                }
            }
            .padding(.trailing, 32)

            Button(action: {
                isSecured.toggle()
            }) {
                Image(systemName: self.isSecured ? "eye.slash" : "eye")
                    .accentColor(.gray)
            }
        }
        .padding(.vertical, 6)
        .padding(.horizontal, 12)
        .background(
            RoundedRectangle(cornerRadius: 100)
                .fill(Color.backgroundSecondary)
        )
        .overlay(border)
    }

    var border: some View {
        RoundedRectangle(cornerRadius: 100)
            .strokeBorder(
                isFocused ? Color.appPrimary : Color.backgroundSecondary,
                lineWidth: isFocused ? 2 : 0
            )
    }
}

struct SecureInput_Previews: PreviewProvider {
    static var previews: some View {
        SecureInput(
            "Hello",
            text: .init(get: { "SomeText" }, set: { _ in })
        )
    }
}
