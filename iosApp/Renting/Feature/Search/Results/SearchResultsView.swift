//
//  SearchResultsView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 23.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct SearchResultsView: View {
    let component: SearchResultsComponent
    @ObservedObject
    private var models: ObservableValue<SearchResultsComponentModel>
    
    init(_ component: SearchResultsComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }
    
    var body: some View {
        let model = models.value
        
        VStack {
            HStack(alignment: .firstTextBaseline) {
                Image(systemName: "arrow.left")
                    .padding(24)
                    .onTapGesture { component.onNavigateBackRequested() }
                SearchInput(component.searchInputComponent)
            }
            Spacer()
                .frame(height: 8)
            PropertyTypeQuickFiltersView(
                filters: model.quickFilters,
                onFilterSelected: component.onQuickFilterToggled,
                clearSelectedFilters: component.onResetQuickFiltersSelected
            )
            Spacer()
                .frame(height: 16)
            searchView
            Spacer()
        }
        .padding(16)
    }
    
    var searchView: some View {
        VStack {
            searchHeaderView
            Spacer()
                .frame(height: 8)
            searchContentView
        }
    }
    
    var searchHeaderView: some View {
        HStack {
            if let count = models.value.searchState.count {
                Text("\(count) found")
                    .font(.title2)
                    .fontWeight(.semibold)
                Spacer()
            } else {
                EmptyView()
            }
        }
    }
    
    var searchContentView: some View {
        ZStack {
            switch models.value.searchState {
            case _ as SearchStateLoading:
                ZStack(alignment: .center) {
                    ProgressView()
                }
            case let state as SearchStateResults:
                PropertySnippetsGrid(
                    snippets: state.snippets,
                    onSnippetClick: component.onSnippetClicked
                )
            case _ as SearchStateEmptyResults:
                // TODO RENTING-49
                Text("Empty results")
            case _ as SearchStateError:
                // TODO RENTING-49
                Text("Error")
            default:
                Text("Unknown")
            }
        }
        .frame(
            minWidth: 0, maxWidth: .infinity,
            minHeight: 0, maxHeight: .infinity
        )
    }
}

private extension SearchState {
    var count: Int? {
        switch self {
        case _ as SearchStateEmptyResults:
            return 0
        case let state as SearchStateResults:
            return state.snippets.count
        default:
            return nil
        }
    }
}

struct SearchResultsView_Previews: PreviewProvider {
    static var previews: some View {
        let quickFilters = PropertyTypeQuickFilters.companion.filtersOrder
            .map { type in
                PropertyTypeQuickFilter(type: type, isApplied: false)
            }
        SearchResultsView(
            DummySearchResultsComponent(
                initModel: SearchResultsComponentModel(
                    quickFilters: PropertyTypeQuickFilters(
                        list: quickFilters
                    ),
                    searchState: SearchStateResults(
                        snippets: [
                            PropertySnippet.preview,
                            PropertySnippet.preview,
                            PropertySnippet.preview
                        ]
                    )
                )
            )
        )
    }
}
