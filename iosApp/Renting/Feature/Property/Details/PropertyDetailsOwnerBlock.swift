//
//  PropertyDetailsOwnerBlock.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsOwnerBlock: View {
    let ownerInfo: ComponentPropertyDetails.OwnerInfo
    let onPhoneClicked: () -> Void
    
    var body: some View {
        HStack {
            Spacer()
            UserAvatar(avatar: ownerInfo.avatar, fullName: ownerInfo.fullName)
                .frame(width: 64, height: 64)
            VStack(alignment: .leading) {
                Text(ownerInfo.fullName)
                    .font(.subheadline)
                    .fontWeight(.semibold)
                Spacer().frame(height: 6)
                Text("Owner")
                    .font(.caption)
                    .fontWeight(.light)
            }
            Spacer()
            Button(
                action: {
                    onPhoneClicked()
                },
                label: {
                    Image(systemName: "phone")
                        .tint(Color.appPrimary)
                }
            )
            .padding(16)
            Spacer()
        }
    }
}

struct PropertyDetailsOwnerBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsOwnerBlock(
            ownerInfo: ComponentPropertyDetailsPreviews().apartment.ownerInfo,
            onPhoneClicked: {}
        )
    }
}
