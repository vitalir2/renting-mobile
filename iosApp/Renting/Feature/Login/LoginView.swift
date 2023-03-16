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

    @State private var showToast = false
    @State private var isShowingPassword = false

    init(_ component: LoginComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }

    var body: some View {
        let model = models.value

        return VStack {
            RentingInput(
                "Login",
                text: Binding(get: { model.login }, set: component.onLoginChanged),
                leadingIcon: Image(systemName: "person.circle")
            )
            SecureInput(
                "Password",
                text: Binding(get: { model.password }, set: component.onPasswordChanged)
            )
            Button("Login", action: {
                component.onLoginStarted()
            })
            .buttonStyle(PrimaryButtonStyle())
            .offset(y: 20)
        }
        .padding(12)
        .onChange(of: model.error, perform: { error in
            showToast = showToast || error != nil
        })
        .toast(
            toastView: ToastView(
                model: ToastData(
                    title: "Something went wrong", delaySeconds: 3
                ),
                onShowed: {
                    component.onLoginErrorShowed()
                },
                isShowed: $showToast
            ),
            show: $showToast
        )
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
