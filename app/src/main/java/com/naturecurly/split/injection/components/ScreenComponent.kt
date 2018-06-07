package com.naturecurly.split.injection.components

import com.naturecurly.split.injection.modules.ScreenModule
import com.naturecurly.split.injection.scopes.PerScreen
import com.naturecurly.split.presentation.ui.bill.BillActivity
import com.naturecurly.split.presentation.ui.people.AddPeopleActivity
import dagger.Component

/**
 * @author Leon Wu
 */

@PerScreen
@Component(dependencies = [ApplicationComponent::class], modules = [ScreenModule::class])
interface ScreenComponent {
    fun inject(activity: BillActivity)
    fun inject(activity: AddPeopleActivity)
}