//
//  LoadedPropertyDetailsView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct LoadedPropertyDetailsView: View {
    let details: ComponentPropertyDetails
    
    var body: some View {
        VStack {
            ScrollView(showsIndicators: false) {
                PropertyDetailsHeader()
                PropertyDetailsMainInfoBlock()
                PropertyDetailsLocationBlock()
                PropertyDetailsOwnerBlock()
                PropertyDetailsDescriptionBlock()
            }
            PropertyDetailsBookingFooter()
        }
    }
}

struct LoadedPropertyDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        LoadedPropertyDetailsView(
            details: ComponentPropertyDetails.companion.preview
        )
    }
}
