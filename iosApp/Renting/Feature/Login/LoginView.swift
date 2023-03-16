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
        let error = LoginError.fromShared(model.error)

        return VStack(spacing: 16) {
            Image("RentingLogoFull")
                .resizable()
                .frame(width: 100, height: 100)
            Spacer()
                .frame(height: 8)
            Text("Create new account")
                .font(.title)
                .fontWeight(.bold)
            Spacer()
                .frame(height: 8)
            RentingInput(
                "Login",
                text: Binding(get: { model.login }, set: component.onLoginChanged),
                leadingIcon: Image(systemName: "person.circle")
            )
            SecureInput(
                "Password",
                text: Binding(get: { model.password }, set: component.onPasswordChanged),
                leadingIcon: Image(systemName: "lock")
            )
            HStack {
                if error == .invalidLoginOrPassword {
                    Text("Invalid login or password")
                        .font(.caption)
                        .foregroundColor(Color.red)
                        .padding(.leading, 16)
                        .offset(y: -8)
                    Spacer()
                }
            }
            Spacer()
                .frame(height: 8)
            Button("Login", action: {
                component.onLoginStarted()
            })
            .buttonStyle(PrimaryButtonStyle())
            HStack {
                Text("Don't have an account?")
                    .foregroundColor(Color.textSecondary)
                Text("Sign up")
                    .foregroundColor(Color.appPrimary)
                    .onTapGesture {
                        component.onRegistrationRequested()
                    }
            }
        }
        .padding(12)
        .onChange(of: error, perform: { error in
            showToast = showToast || error == .unknown
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
