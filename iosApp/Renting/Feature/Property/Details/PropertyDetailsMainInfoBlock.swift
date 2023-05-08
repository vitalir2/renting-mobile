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
    let mainInfo: ComponentPropertyDetailsMainInfo
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(mainInfo.price)
                .font(.headline)
            Spacer()
                .frame(height: 12)
            switch mainInfo {
            case let familyHouse as ComponentPropertyDetailsMainInfoFamilyHouse:
                HStack(alignment: .top, spacing: mainFeaturesSpacing) {
                    MainInfoFeature(title: familyHouse.area_, subtitle: "area")
                    MainInfoFeature(title: familyHouse.renovationType, subtitle: "renovation")
                    MainInfoFeature(title: familyHouse.numberOfRooms, subtitle: "total")
                }
            case let apartment as ComponentPropertyDetailsMainInfoApartment:
                HStack(alignment: .top, spacing: mainFeaturesSpacing) {
                    MainInfoFeature(title: apartment.area_, subtitle: "area")
                    MainInfoFeature(title: apartment.floor, subtitle: "floor")
                    MainInfoFeature(title: apartment.numberOfRooms, subtitle: "total")
                }
            case let land as ComponentPropertyDetailsMainInfoLand:
                HStack(alignment: .top) {
                    Text("Area:")
                        .font(.title3)
                        .fontWeight(.semibold)
                    Text(land.area_)
                        .font(.body)
                }
            default:
                EmptyView()
            }
        }
    }
}

private let mainFeaturesSpacing = CGFloat(16)

private struct MainInfoFeature: View {
    let title: String
    let subtitle: String
    
    var body: some View {
        VStack(alignment: .center) {
            Text(title)
                .font(.subheadline)
            Text(subtitle)
                .font(.caption)
                .foregroundColor(Color.textSecondary)
        }
    }
}

struct PropertyDetailsMainInfoBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsMainInfoBlock(
            mainInfo: ComponentPropertyDetailsPreviews.shared
                .apartment.mainInfo
        )
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
    }
}
