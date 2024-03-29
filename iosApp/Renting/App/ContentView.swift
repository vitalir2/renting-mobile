import SwiftUI
import shared

struct ContentView: View {
    @State
    private var componentHolder = ComponentHolder {
        DefaultRootComponent(
            componentContext: $0,
            storeFactory: DefaultStoreFactory(),
            rootGraph: DefaultRootGraph(
                settingsFactory: SettingsFactory()
            )
        )
    }

	var body: some View {
        RootView(component: componentHolder.component)
            .onAppear { LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle) }
            .splashScreen {
                ZStack {
                    Image("RentingLogoFull")
                        .resizable()
                        .frame(width: 100, height: 100)
                }
            }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
