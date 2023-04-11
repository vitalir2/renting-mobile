//
//  HomeToolbarView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 01.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct HomeToolbarView: View {
    let userInfo: UserInfo
    
    var body: some View {
        HStack {
            RentingImage(image: userInfo.avatar) { phase in
                if let image = phase.image {
                    image.resizable()
                        .clipShape(Circle())
                } else {
                    placeholder
                }
            }
            .frame(width: 40, height: 40)
            
            Spacer()
                .frame(width: 16)
            
            VStack(alignment: .leading, spacing: 2) {
                Text("Hello 👋")
                    .font(.body)
                    .foregroundColor(.textSecondary)
                Text(userInfo.fullName)
                    .font(.subheadline)
                    .fontWeight(.semibold)
            }
            
            Spacer()
        }
        .padding(16)
    }
    
    var placeholder: some View {
        ZStack(alignment: .center) {
            Circle().foregroundColor(Color.appPrimary)
            if let firstNameCapitalizedChar = userInfo.firstName.first?.uppercased(),
               let lastNameCapitalizedChar = userInfo.lastName.first?.uppercased() {
                let placeholderText = "\(firstNameCapitalizedChar)\(lastNameCapitalizedChar)"
                Text(placeholderText)
                    .foregroundColor(Color.onPrimary)
                    .font(.body)
            }
        }
    }
}

struct HomeToolbarView_Previews: PreviewProvider {
    static var previews: some View {
        HomeToolbarView(
            userInfo: UserInfo(
                login: "Login",
                firstName: "Kenny",
                lastName: "Jeddy",
                avatar: SharedImageUrl(path: "something")
            )
        )
    }
}
