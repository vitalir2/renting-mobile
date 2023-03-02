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
                text: Binding(get: { model.login }, set: component.onLoginInputChanged)
            )
            TextField(
                "Password",
                text: Binding(get: { model.password }, set: component.onPasswordInputChanged)
            )
            .foregroundColor(Color.appPrimary)
        }
        .padding(12)
        .textFieldStyle(.roundedBorder)
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(StubComponent())
    }

    class StubComponent: LoginComponent {
        let models: Value<LoginComponentModel> = valueOf(
            LoginComponentModel(login: "Vitalir", password: "123")
        )

        func onLoginInputChanged(login: String) {
        }

        func onPasswordInputChanged(password: String) {
        }
    }
}