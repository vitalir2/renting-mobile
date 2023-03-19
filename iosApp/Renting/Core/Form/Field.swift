//
//  Field.swift
//  Renting
//
//  Created by Vitaly Khvostov on 19.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import Foundation

struct Field: Identifiable {
    let fieldId: Id
    let value: String
    let error: String?
    
    var id: String { fieldId.id }
    
    enum Kind {
        case login
        case password
        case email
        case phoneNumber
        case firstName
        case lastName
        
        var name: String {
            switch self {
            case .login:
                return "login"
            case .password:
                return "password"
            case .email:
                return "email"
            case .firstName:
                return "firstName"
            case .lastName:
                return "lastName"
            case .phoneNumber:
                return "phoneNumber"
            }
        }
        
        var placeholder: String {
            switch self {
            case .login:
                return "Login"
            case .password:
                return "Password"
            case .email:
                return "Email"
            case .phoneNumber:
                return "Phone number"
            case .firstName:
                return "First name"
            case .lastName:
                return "Last name"
            }
        }
    }
    
    struct Id: Identifiable, Hashable {
        let kind: Kind
        let index: Int32
        
        var id: String { kind.name + String(index) }
        
        func hash(into hasher: inout Hasher) {
            kind.hash(into: &hasher)
            index.hash(into: &hasher)
        }
    }
}
