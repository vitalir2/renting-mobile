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

    init(_ component: RegistrationComponent) {
        self.component = component
    }

    var body: some View {
        Text("Registration screen!")
    }
}

struct RegistrationView_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationView(StubComponent())
    }

    class StubComponent: RegistrationComponent {
        var models: Value<RegistrationComponentModel> = valueOf(
            RegistrationComponentModel(
                registrationForm: [
                    SharedTextField(id: SharedTextField.Id.login, value: "", error: nil)
                ]
            )
        )

        func onFieldChanged(id: SharedTextField.Id, value: String) {
        }

        func completeRegistration() {
        }
    }
}
