//
//  PropertyDetailsMainInfoBlock.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsMainInfoBlock: View {
    let mainInfo: ComponentPropertyDetails.MainInfo
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(mainInfo.price)
                .font(.headline)
            Spacer()
                .frame(height: 8)
            Text(mainInfo.area)
                .font(.body)
            Text("area")
                .font(.caption)
                .foregroundColor(Color.textSecondary)
        }
    }
}

struct PropertyDetailsMainInfoBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsMainInfoBlock(
            mainInfo: ComponentPropertyDetails.companion.preview.mainInfo
        )
    }
}
