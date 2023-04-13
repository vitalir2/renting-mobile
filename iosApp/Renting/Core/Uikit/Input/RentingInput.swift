//
//  RentingInput.swift
//  Renting
//
//  Created by Vitaly Khvostov on 12.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct RentingInput<LeadingIcon, TrailingIcon>: View
where LeadingIcon: View, TrailingIcon: View {
    let title: String
    @Binding var text: String
    var leadingIcon: LeadingIcon?
    var trailingIcon: TrailingIcon?
    var error: String?

    @FocusState private var isEditing: Bool

    init(
        _ title: String,
        text: Binding<String>,
        leadingIcon: (() -> LeadingIcon)? = nil,
        trailingIcon: (() -> TrailingIcon)? = nil,
        error: String? = nil
    ) {
        self.title = title
        self._text = text
        self.leadingIcon = leadingIcon?()
        self.trailingIcon = trailingIcon?()
        self.error = error
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
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
            
            Text(error ?? "")
                .foregroundColor(Color.red)
                .font(.caption)
                .padding(.leading, 24)
                .opacity(error != nil ? 1 : 0)
        }
    }

    var field: some View {
        TextField(title, text: $text)
            .multilineTextAlignment(.leading)
            .accentColor(Color.onBackgroundSecondary)
            .foregroundColor(Color.onBackgroundSecondary)
            .font(.body)
            .focused($isEditing)
            .textInputAutocapitalization(.never)
    }

    var border: some View {
        RoundedRectangle(cornerRadius: 100)
            .strokeBorder(
                isEditing ? Color.appPrimary : Color.backgroundSecondary,
                lineWidth: isEditing ? 2 : 0
            )
    }
}

extension RentingInput where LeadingIcon == EmptyView {
    init(
        _ title: String,
        text: Binding<String>,
        trailingIcon: (() -> TrailingIcon)? = nil,
        error: String? = nil
    ) {
        self.title = title
        self._text = text
        self.leadingIcon = nil
        self.trailingIcon = trailingIcon?()
        self.error = error
    }
}

extension RentingInput where TrailingIcon == EmptyView {
    init(
        _ title: String,
        text: Binding<String>,
        leadingIcon: (() -> LeadingIcon)? = nil,
        error: String? = nil
    ) {
        self.title = title
        self._text = text
        self.leadingIcon = leadingIcon?()
        self.trailingIcon = nil
        self.error = error
    }
}

extension RentingInput
where LeadingIcon == EmptyView, TrailingIcon == EmptyView {
    init(
        _ title: String,
        text: Binding<String>,
        error: String? = nil
    ) {
        self.title = title
        self._text = text
        self.leadingIcon = nil
        self.trailingIcon = nil
        self.error = error
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
            leadingIcon: {
                Image(systemName: "person.circle")
            },
            trailingIcon: {
                Image(systemName: "eye")
            },
            error: "Error"
        )
    }
}
