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
    @State private var showInDevelopmentToast = false
    
    let details: ComponentPropertyDetails
    
    var body: some View {
        VStack(alignment: .center) {
            ScrollView(showsIndicators: false) {
                PropertyDetailsHeader(
                    images: details.images
                )
                .frame(minWidth: 0, maxWidth: .infinity)
                Spacer().frame(height: 8)
                VStack(alignment: .leading) {
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
                }
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                .padding(8)
            }
            PropertyDetailsBookingFooter(
                showToast: $showInDevelopmentToast
            )
        }
        .toast(
            toastView: ToastView(
                model: ToastData(
                    title: "In development", delaySeconds: 2
                ),
                isShowed: $showInDevelopmentToast
            ),
            show: $showInDevelopmentToast
        )
    }
}

struct LoadedPropertyDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        LoadedPropertyDetailsView(
            details: ComponentPropertyDetailsPreviews.shared.apartment
        )
    }
}
