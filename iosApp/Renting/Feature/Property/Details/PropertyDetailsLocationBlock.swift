//
//  PropertyDetailsLocationBlock.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsLocationBlock: View {
    let location: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Location")
                .font(.headline)
                .fontWeight(.semibold)
            Spacer().frame(height: 12)
            HStack {
                Image(systemName: "location")
                    .tint(Color.appPrimary)
                Spacer().frame(width: 8)
                Text(location)
                    .font(.body)
            }
        }
    }
}

struct PropertyDetailsLocationBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsLocationBlock(
            location: ComponentPropertyDetailsPreviews().apartment.location
        )
    }
}
