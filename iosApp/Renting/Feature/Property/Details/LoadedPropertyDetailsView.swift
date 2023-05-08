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
        VStack(alignment: .leading) {
            ScrollView(showsIndicators: false) {
                PropertyDetailsHeader(
                    images: details.images
                )
                .frame(minWidth: 0, maxWidth: .infinity)
                Spacer().frame(height: 8)
                PropertyDetailsMainInfoBlock(
                    mainInfo: details.mainInfo
                )
                PropertyDetailsLocationBlock()
                PropertyDetailsOwnerBlock(
                    ownerInfo: details.ownerInfo,
                    onPhoneClicked: {
                        if let url = URL(string: "telprompt:\(details.ownerInfo.phoneNumber)") {
                            if UIApplication.shared.canOpenURL(url) {
                                UIApplication.shared.open(url)
                            }
                        }
                    }
                )
                PropertyDetailsDescriptionBlock()
            }
            .frame(minWidth: 0, maxWidth: .infinity)
            PropertyDetailsBookingFooter()
        }
    }
}

struct LoadedPropertyDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        LoadedPropertyDetailsView(
            details: ComponentPropertyDetailsPreviews.shared.apartment
        )
    }
}
