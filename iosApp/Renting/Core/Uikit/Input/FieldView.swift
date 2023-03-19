//
//  FieldView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 19.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct FieldView: View {
    private var field: Field
    @State private var text: String
    
    private let onChanged: (String) -> Void
    
    init(_ field: Field, onChanged: @escaping (String) -> Void) {
        self.field = field
        self.text = field.value
        self.onChanged = onChanged
    }
    
    var body: some View {
        let kind = field.fieldId.kind
        let placeholder = kind.placeholder
        
        Group {
            switch kind {
            case .login, .firstName, .lastName:
                RentingInput(placeholder, text: $text, error: field.error)
                    .textInputAutocapitalization(.words)
            case .password:
                SecureInput(placeholder, text: $text, error: field.error)
            case .email:
                RentingInput(placeholder, text: $text, error: field.error)
                    .keyboardType(.emailAddress)
            case .phoneNumber:
                RentingInput(placeholder, text: $text, error: field.error)
                    .keyboardType(.phonePad)
            }
        }
        .onChange(of: text) { value in
            onChanged(value)
        }
    }
}
