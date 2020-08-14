[![](https://jitpack.io/v/jaceed/easy-decorations.svg)](https://jitpack.io/#jaceed/easy-decorations)

# easy-decorations

Custom item divider decorations for RecyclerView.

## Usage

Use `gradle`

First, add it in your root build.gradle at the end of repositories:

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then, add the dependency:

```gradle
	dependencies {
	        implementation 'com.github.jaceed:easy-decorations:1.0.0'
	}
```

## Decorations

The following two supported, which are only for **LinearLayoutManger**.

- `MiddleDividerItemDecoration`: decorations only between every item-pair, that is, the last item excluded

```kotlin
val decor = MiddleDividerItemDecoration(context, LinearDecoration.VERTICAL)
```

- `RangedDividerItemDecoration`: support range setting for decorations, which means we can add decorations only for those expected by index range

```kotlin
val decor = RangedDividerItemDecoration(context, LinearDecoration.HORIZONTAL, 2, 5, true)
```
