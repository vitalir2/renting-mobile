//
//  PropertyDetailsBuildingDescriptionBlock.swift
//  Renting
//
//  Created by Vitaly Khvostov on 09.05.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsBuildingDescriptionBlock: View {
    let buildingInfo: ComponentPropertyDetails.BuildingInfo
    
    var body: some View {
        VStack(alignment: .leading, spacing: 3) {
            Text("About building")
                .font(.headline)
                .fontWeight(.semibold)
            Spacer().frame(height: 4)
            BuildingFeature(title: "Building year", value: buildingInfo.buildingYear)
            BuildingFeature(title: "Number of floors", value: buildingInfo.numberOfFloors)
            BuildingFeature(title: "Type", value: buildingInfo.buildingType)
            BuildingFeature(title: "Material", value: buildingInfo.material)
        }
    }
}

private struct BuildingFeature: View {
    let title: String
    let value: String
    
    var body: some View {
            HStack {
                Text(title)
                    .font(.subheadline)
                    .foregroundColor(Color.textSecondary)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text(value)
                    .font(.body)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
    }
}

struct PropertyDetailsBuildingDescriptionBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsBuildingDescriptionBlock(
            buildingInfo: ComponentPropertyDetailsPreviews().apartment.buildingInfo!
        )
    }
}
