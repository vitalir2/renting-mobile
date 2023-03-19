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
    
    @FocusState
    private var showError: Field.Id?
    
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
                .focused($showError, equals: field.fieldId)
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
        .onChange(of: model.scrollToErrorFieldId) { value in
            showError = value?.toSwiftModel()
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
