package me.yifeiyuan.adh.showcase

/**
 * Created by 程序亦非猿 on 2021/1/19.
 */
interface AdhShowcaseProvider {
    fun provideShowcaseItems(): List<AdhShowcaseItem>
}