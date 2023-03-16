//
//  RentingInput.swift
//  Renting
//
//  Created by Vitaly Khvostov on 12.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct RentingInput: View {
    let title: String
    @Binding var text: String
    var leadingIcon: Image?
    var trailingIcon: Image?

    @FocusState private var isEditing: Bool

    init(
        _ title: String,
        text: Binding<String>,
        leadingIcon: Image? = nil,
        trailingIcon: Image? = nil
    ) {
        self.title = title
        self._text = text
        self.leadingIcon = leadingIcon
        self.trailingIcon = trailingIcon
    }

    var body: some View {
        HStack {
            leadingIcon
            field
            trailingIcon
        }
        .padding(.vertical, 6)
        .padding(.horizontal, 12)
        .background(
            RoundedRectangle(cornerRadius: 100)
                .fill(Color.backgroundSecondary)
        )
        .overlay(border)
    }

    var field: some View {
        TextField(title, text: $text)
            .multilineTextAlignment(.leading)
            .accentColor(Color.onBackgroundSecondary)
            .foregroundColor(Color.onBackgroundSecondary)
            .font(.body)
            .focused($isEditing)
    }

    var border: some View {
        RoundedRectangle(cornerRadius: 100)
            .strokeBorder(
                isEditing ? Color.appPrimary : Color.backgroundSecondary,
                lineWidth: isEditing ? 2 : 0
            )
    }
}

struct RentingInput_Previews: PreviewProvider {
    static var previews: some View {
        RentingInput(
            "Title",
            text: .init(
                get: { "Hello" },
                set: { _ in }
            ),
            leadingIcon: Image(systemName: "person.circle"),
            trailingIcon: Image(systemName: "eye")
        )
    }
}
