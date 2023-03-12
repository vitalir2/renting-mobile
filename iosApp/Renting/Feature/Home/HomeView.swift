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

    init(_ component: HomeComponent) {
        self.component = component
    }

    var body: some View {
        Text("Home screen!")
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(StubComponent())
    }

    class StubComponent: HomeComponent {

    }
}
