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
            if model.token.isEmpty {
                TextField(
                    "Login",
                    text: Binding(get: { model.login }, set: component.onLoginInputChanged)
                )
                TextField(
                    "Password",
                    text: Binding(get: { model.password }, set: component.onPasswordInputChanged)
                )
                Button("Login", action: {
                    component.onLoginStarted()
                })
                .buttonStyle(PrimaryButtonStyle())
                .offset(y: 20)
            } else {
                Text(model.token)
                    .foregroundColor(Color.accentColor)
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
            LoginComponentModel(login: "Vitalir", password: "123", token: "")
        )

        func onLoginInputChanged(login: String) {
        }

        func onPasswordInputChanged(password: String) {
        }

        func onLoginStarted() {
        }
    }
}
