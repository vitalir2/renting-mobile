//
//  PropertyDetailsView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 02.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsView: View {
    let component: PropertyDetailsComponent
    
    init(_ component: PropertyDetailsComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack {
            HStack {
                backButton
                Text("Property Details")
                Spacer()
            }
            .padding(16)
            Spacer()
            Text("Property Details")
            Spacer()
        }
    }
    
    var backButton: some View {
        Button {
            component.onBackButtonClick()
        } label: {
            Image(systemName: "arrow.backward")
        }
    }
}

struct PropertyDetailsView_Previews: PreviewProvider {
    class Stub: PropertyDetailsComponent {
        func onBackButtonClick() {
        }
    }
    
    static var previews: some View {
        PropertyDetailsView(Stub())
    }
}
