//
//  PropertyPreview.swift
//  Renting
//
//  Created by Vitaly Khvostov on 02.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import Foundation
import shared

extension PropertySnippet {
    static var preview: PropertySnippet {
        PropertySnippet.companion.previewId += 1
        return PropertySnippet(
            id: PropertySnippet.companion.previewId,
            type: PropertyType.familyHouse,
            location: "2857 E Detroit St, Chandler, AZ 85225",
            image: SharedImageUrl(path: "https://photos.zillowstatic.com/fp/e57899af93feb02883e71c4a155c859f-p_e.jpg"),
            price: 20223
        )
    }
}
