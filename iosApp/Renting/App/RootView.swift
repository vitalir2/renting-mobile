//
//  RootView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 26.02.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct RootView: View {
    @ObservedObject
    private var childStack: ObservableValue<ChildStack<AnyObject, RootComponentChild>>

    init(component: RootComponent) {
        self.childStack = ObservableValue(component.childStack)
    }

    var body: some View {
        let child = self.childStack.value.active.instance

        switch child {
        case let login as RootComponentChildLogin:
            LoginView(login.component)
        default: EmptyView()
        }
    }
}

struct RootView_Previews: PreviewProvider {
    static var previews: some View {
        RootView(component: StubComponent())
    }

    class StubComponent: RootComponent {
        var childStack: Value<ChildStack<AnyObject, RootComponentChild>> = valueOf(
            ChildStack(
                active: ChildCreated(
                    configuration: "config" as AnyObject,
                    instance: RootComponentChildLogin(component: LoginView_Previews.StubComponent())
                ),
                backStack: []
            )
        )
    }
}
