//
//  RegistrationView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 11.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct RegistrationView: View {
    private let component: RegistrationComponent
    
    @ObservedObject
    private var models: ObservableValue<RegistrationComponentModel>

    init(_ component: RegistrationComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }
    
    var body: some View {
        let model = models.value
        
        VStack(alignment: .center, spacing: 12) {
            Text("Create New Account")
                .font(.title)
                .fontWeight(.bold)
            
            ForEach(model.registrationForm.swiftFields()) { field in
                FieldView(
                    field,
                    onChanged: {
                        component.onFieldChanged(id: field.fieldId.toSharedModel(), value: $0)
                    }
                )
            }
            Spacer()
                .frame(height: 8)
            Button(
                action: component.completeRegistration,
                label: {
                    Text("Register")
                }
            )
            .buttonStyle(PrimaryButtonStyle())
            
            HStack {
                Text("Already have an account?")
                Text("Sign in")
                    .foregroundColor(Color.appPrimary)
                    .fontWeight(.semibold)
                    .onTapGesture {
                        component.onLoginRequired()
                    }
            }
            Spacer()
        }
        .padding()
    }
}

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

struct Field: Identifiable {
    let fieldId: Id
    let value: String
    let error: String?
    
    var id: String { fieldId.id }
    
    enum Kind {
        case login
        case password
        case email
        case phoneNumber
        case firstName
        case lastName
        
        var name: String {
            switch self {
            case .login:
                return "login"
            case .password:
                return "password"
            case .email:
                return "email"
            case .firstName:
                return "firstName"
            case .lastName:
                return "lastName"
            case .phoneNumber:
                return "phoneNumber"
            }
        }
        
        var placeholder: String {
            switch self {
            case .login:
                return "Login"
            case .password:
                return "Password"
            case .email:
                return "Email"
            case .phoneNumber:
                return "Phone number"
            case .firstName:
                return "First name"
            case .lastName:
                return "Last name"
            }
        }
    }
    
    struct Id: Identifiable {
        let kind: Kind
        let index: Int32
        
        var id: String { kind.name + String(index) }
    }
}

extension FieldForm {
    func swiftFields() -> [Field] {
        return self.fields.map { sharedField in
            Field(
                fieldId: Field.Id(
                    kind: sharedField.id.kind.toSwiftModel(),
                    index: sharedField.id.index
                ),
                value: sharedField.value,
                error: sharedField.error
            )
        }
    }
}

extension SharedTextField.Kind {
    func toSwiftModel() -> Field.Kind {
        switch self {
        case .login:
            return .login
        case .password:
            return .password
        case .email:
            return .email
        case .phoneNumber:
            return .phoneNumber
        case .firstName:
            return .firstName
        case .lastName:
            return .lastName
        default:
            // Impossible case
            return .login
        }
    }
}

extension Field.Id {
    func toSharedModel() -> SharedTextField.Id {
        return SharedTextField.Id(kind: self.kind.toSharedModel(), index: self.index)
    }
}

extension Field.Kind {
    func toSharedModel() -> SharedTextField.Kind {
        switch self {
        case .login:
            return .login
        case .password:
            return .password
        case .email:
            return .email
        case .phoneNumber:
            return .phoneNumber
        case .firstName:
            return .firstName
        case .lastName:
            return .lastName
        }
    }
}

struct RegistrationView_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationView(StubComponent())
    }
    
    class StubComponent: RegistrationComponent {
        var models: Value<RegistrationComponentModel> = valueOf(
            RegistrationComponentModel(
                registrationForm: FieldForm(
                    fields: [
                        SharedTextField(
                            id: SharedTextField.Id(
                                kind: SharedTextField.Kind.login,
                                index: 0
                            ),
                            value: "Hello",
                            error: "World"
                        ),
                        SharedTextField(kind: SharedTextField.Kind.password),
                        SharedTextField(kind: SharedTextField.Kind.email),
                        SharedTextField(kind: SharedTextField.Kind.phoneNumber),
                        SharedTextField(kind: SharedTextField.Kind.firstName),
                        SharedTextField(kind: SharedTextField.Kind.lastName)
                    ]
                ),
                isRegistering: false,
                scrollToErrorFieldId: nil
            )
        )
        
        func onFieldChanged(id: SharedTextField.Id, value: String) {
        }
        
        func onLoginRequired() {
        }
        
        func onScrollToErrorCompleted() {
        }
        
        func completeRegistration() {
        }
    }
}
