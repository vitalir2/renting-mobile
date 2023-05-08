//
//  UserAvatar.swift
//  Renting
//
//  Created by Vitaly Khvostov on 08.05.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct UserAvatar: View {
    
    let avatar: SharedImage?
    let fullName: String
    
    var body: some View {
        ZStack {
            RentingImage(image: avatar) { phase in
                if let image = phase.image {
                    image.resizable()
                        .clipShape(Circle())
                } else {
                    placeholder
                }
            }
        }
    }
    
    var placeholder: some View {
        let names = fullName.split(separator: " ")
        let firstName = names.first
        let lastName = names.dropFirst().first
        return ZStack(alignment: .center) {
            Circle().foregroundColor(Color.appPrimary)
            if let firstNameCapitalizedChar = firstName?.first?.uppercased(),
               let lastNameCapitalizedChar = lastName?.first?.uppercased() {
                let placeholderText = "\(firstNameCapitalizedChar)\(lastNameCapitalizedChar)"
                Text(placeholderText)
                    .foregroundColor(Color.onPrimary)
                    .font(.body)
            }
        }
    }
}

struct UserAvatar_Previews: PreviewProvider {
    static var previews: some View {
        UserAvatar(
            avatar: nil, fullName: "Jenny Kerry"
        )
    }
}
