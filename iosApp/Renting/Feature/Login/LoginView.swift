//
//  LoginView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 26.02.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    private let component: LoginComponent

    @ObservedObject
    private var models: ObservableValue<LoginComponentModel>

    init(_ component: LoginComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }

    var body: some View {
        let model = models.value

        return VStack {
            TextField(
                "Login",
                text: Binding(get: { model.login }, set: component.onLoginChanged)
            )
            TextField(
                "Password",
                text: Binding(get: { model.password }, set: component.onPasswordChanged)
            )
            Button("Login", action: {
                component.onLoginStarted()
            })
            .buttonStyle(PrimaryButtonStyle())
            .offset(y: 20)

            if let error = model.error {
                Spacer()
                Text("Error \(error)")
            }
        }
        .padding(12)
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(StubComponent())
    }

    class StubComponent: LoginComponent {
        let models: Value<LoginComponentModel> = valueOf(
            LoginComponentModel(login: "Vitalir", password: "123", error: nil)
        )

        func onLoginChanged(login: String) {
        }

        func onPasswordChanged(password: String) {
        }

        func onLoginStarted() {
        }

        func onLoginCompleted() {
        }

        func onRegistrationRequested() {
        }
        
        func onLoginErrorShowed() {
        }
    }
}
