//
//  RentingErrorPlaceholder.swift
//  Renting
//
//  Created by Vitaly Khvostov on 27.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct RentingErrorPlaceholder: View {
    let action: RentingButtonAction
    
    var body: some View {
        VStack {
            Text("Oops, something went wrong")
            Text("Please try again, or return to the previous page")
            Button(
                action: action.handler,
                label: {
                    Text(action.title)
                }
            )
            .buttonStyle(PrimaryButtonStyle())
        }
    }
}

struct RentingButtonAction {
    let title: String
    let handler: () -> Void
}

struct RentingErrorPlaceholder_Previews: PreviewProvider {
    static var previews: some View {
        RentingErrorPlaceholder(
            action: RentingButtonAction(
                title: "Refresh",
                handler: {}
            )
        )
    }
}
