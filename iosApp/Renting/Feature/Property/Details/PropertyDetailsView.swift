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
    
    @ObservedObject
    private var models: ObservableValue<PropertyDetailsComponentModel>
    
    init(_ component: PropertyDetailsComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }
    
    var body: some View {
        let model = models.value
        
        VStack {
            HStack {
                backButton
                Text("Property Details")
                Spacer()
            }
            .padding(16)
            Spacer()
            switch model {
            case _ as PropertyDetailsComponentModelLoading:
                ProgressView()
            case let detailsLoaded as PropertyDetailsComponentModelPropertyDetailsLoaded:
                LoadedPropertyDetailsView(
                    details: detailsLoaded.details
                )
            default:
                Text("Impossible case")
            }
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
        var models: Value<PropertyDetailsComponentModel> = valueOf(
            PropertyDetailsComponentModelLoading()
        )
        func onBackButtonClick() {
        }
    }
    
    static var previews: some View {
        PropertyDetailsView(Stub())
    }
}
