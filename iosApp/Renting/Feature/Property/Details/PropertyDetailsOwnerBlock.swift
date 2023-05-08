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
    
    var body: some View {
        HStack {
            UserAvatar(avatar: ownerInfo.avatar, fullName: ownerInfo.fullName)
                .frame(width: 64, height: 64)
        }
    }
}

struct PropertyDetailsOwnerBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsOwnerBlock(
            ownerInfo: ComponentPropertyDetailsPreviews().apartment.ownerInfo
        )
    }
}
