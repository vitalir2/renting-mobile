//
//  HomeView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 11.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    private let component: HomeComponent
    
    @ObservedObject
    private var models: ObservableValue<HomeComponentModel>

    init(_ component: HomeComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }

    var body: some View {
        let model = models.value
        
        VStack {
            if let userInfo = model.userInfo {
                HomeToolbarView(userInfo: userInfo)
            } else {
                Text("Loading")
            }
            Spacer()
            Button(
                action: {
                    component.logout()
                },
                label: {
                    Text("Logout")
                }
            )
            .buttonStyle(PrimaryButtonStyle())
        }
        .padding()
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(StubComponent())
    }

    class StubComponent: HomeComponent {
        var models: Value<HomeComponentModel> = valueOf(
            HomeComponentModel(
                userInfo: UserInfo(
                    login: "Login",
                    firstName: "Jenny",
                    lastName: "Keddy",
                    imageUrl: "Image url"
                )
            )
        )
        
        func logout() {
        }
    }
}
