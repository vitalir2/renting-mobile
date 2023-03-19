//
//  FieldMappers.swift
//  Renting
//
//  Created by Vitaly Khvostov on 19.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import shared

extension FieldForm {    
    func swiftFields() -> [Field] {
        return self.fields.map { sharedField in
            Field(
                fieldId: sharedField.id.toSwiftModel(),
                value: sharedField.value,
                error: sharedField.error
            )
        }
    }
}

extension SharedTextField.Id {
    func toSwiftModel() -> Field.Id {
        return Field.Id(
            kind: self.kind.toSwiftModel(),
            index: self.index
        )
    }
}

extension SharedTextField.Kind {
    func toSwiftModel() -> Field.Kind {
        switch self {
        case .login:
            return .login
        case .password:
            return .password
        case .email:
            return .email
        case .phoneNumber:
            return .phoneNumber
        case .firstName:
            return .firstName
        case .lastName:
            return .lastName
        default:
            // Impossible case
            return .login
        }
    }
}

extension Field.Id {
    func toSharedModel() -> SharedTextField.Id {
        return SharedTextField.Id(kind: self.kind.toSharedModel(), index: self.index)
    }
}

extension Field.Kind {
    func toSharedModel() -> SharedTextField.Kind {
        switch self {
        case .login:
            return .login
        case .password:
            return .password
        case .email:
            return .email
        case .phoneNumber:
            return .phoneNumber
        case .firstName:
            return .firstName
        case .lastName:
            return .lastName
        }
    }
}
