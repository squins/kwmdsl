package com.squins.kwmdsl

import java.net.URI
import java.net.URL
import java.nio.charset.Charset
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.temporal.ChronoUnit.MINUTES
import java.util.Locale
import java.util.Properties
import java.util.regex.Pattern

/**
 * See [`accept`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/accept).
 */
fun attrAccept(fileTypes: String) = attr("accept", fileTypes)

/**
 * See [`accept-charset`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#accept-charset).
 */
fun attrAcceptCharset() = attr("accept-charset", "UTF-8")

/**
 * See [`accesskey`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/accesskey).
 */
fun attrAccesskey(character: Char) = attr("accesskey", character)

/**
 * See [`accesskey`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/accesskey).
 */
fun attrAccesskey(character: String) = attr("accesskey", character)

/**
 * See [`action`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#action).
 */
fun attrAction(url: URL) = attr("action", url.toString())

/**
 * See [`action`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#action).
 */
fun attrAction(url: String) = attr("action", url)

/**
 * See [`allow`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/iframe#allow).
 */
fun attrAllow(permissionsPolicy: String) = attr("allow", permissionsPolicy)

/**
 * See [`alpha`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/color#alpha).
 */
fun attrAlpha() = attr("alpha", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrAlt(text: String) = attr("alt", text)

/**
 * See [`as`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/link#as).
 */
fun attrAs(potentialDestination: String) = attr("as", potentialDestination)

/**
 * See [`async`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/script#async).
 */
fun attrAsync() = attr("async", "")

enum class AutocapitalizeManner {
    CHARACTERS,
    NONE,
    SENTENCES,
    WORDS,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`autocapitalize`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/autocapitalize).
 */
fun attrAutocapitalize(manner: AutocapitalizeManner) = attr("autocapitalize", manner.value)

/**
 * See [`autocapitalize`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/autocapitalize).
 */
fun attrAutocapitalize(manner: String) = attr("autocapitalize", manner)

enum class AutocompleteDetail {
    ADDITIONAL_NAME,
    ADDRESS_LEVEL1,
    ADDRESS_LEVEL2,
    ADDRESS_LEVEL3,
    ADDRESS_LEVEL4,
    ADDRESS_LINE1,
    ADDRESS_LINE2,
    ADDRESS_LINE3,
    BDAY,
    BDAY_DAY,
    BDAY_MONTH,
    BDAY_YEAR,
    CC_ADDITIONAL_NAME,
    CC_CSC,
    CC_EXP,
    CC_EXP_MONTH,
    CC_EXP_YEAR,
    CC_FAMILY_NAME,
    CC_GIVEN_NAME,
    CC_NAME,
    CC_NUMBER,
    CC_TYPE,
    COUNTRY,
    COUNTRY_NAME,
    CURRENT_PASSWORD,
    FAMILY_NAME,
    GIVEN_NAME,
    HONORIFIC_PREFIX,
    HONORIFIC_SUFFIX,
    LANGUAGE,
    NAME,
    NEW_PASSWORD,
    NICKNAME,
    ONE_TIME_CODE,
    ORGANIZATION,
    ORGANIZATION_TITLE,
    PHOTO,
    POSTAL_CODE,
    SEX,
    STREET_ADDRESS,
    TRANSACTION_AMOUNT,
    TRANSACTION_CURRENCY,
    URL,
    USERNAME,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`autocomplete`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/autocomplete).
 */
fun attrAutocomplete(state: Boolean) = attr("autocomplete", if (state) "on" else "off")

/**
 * See [`autocomplete`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/autocomplete).
 */
fun attrAutocomplete(vararg details: AutocompleteDetail) =
    attr("autocomplete", details.joinToString(" ") { it.value })

/**
 * See [`autocomplete`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/autocomplete).
 */
fun attrAutocomplete(details: String) = attr("autocomplete", details)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrAutoplay() = attr("autoplay", "")

enum class CaptureFacingMode {
    ENVIRONMENT,
    USER,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`capture`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/capture).
 */
fun attrCapture(facingMode: CaptureFacingMode) = attr("capture", facingMode.value)

/**
 * See [`capture`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/capture).
 */
fun attrCapture(facingMode: String) = attr("capture", facingMode)

/**
 * See [`charset`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meta#charset).
 */
fun attrCharset(characterSet: Charset) = attr("charset", characterSet.name())

/**
 * See [`charset`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meta#charset).
 */
fun attrCharset(characterSet: String) = attr("charset", characterSet)

/**
 * See [`checked`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input#checked).
 */
fun attrChecked() = attr("checked", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrCite(uri: URI) = attr("alt", uri.toASCIIString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrCite(uri: String) = attr("alt", uri)

/**
 * See [`class`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/class).
 */
fun attrClass(classes: String) = attr("class", classes)

enum class Colorspace {
    DISPLAY_P3,
    LIMITED_SRGB,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`colorspace`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/color#colorspace).
 */
fun attrColorspace(colorspace: Colorspace) = attr("colorspace", colorspace.value)

/**
 * See [`colorspace`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/color#colorspace).
 */
fun attrColorspace(colorspace: String) = attr("colorspace", colorspace)

/**
 * See [`cols`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/textarea#cols).
 */
fun attrCols(numberOfColumns: Int) = attr("cols", numberOfColumns)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrColspan(numberOfColumns: Int) = attr("colspan", numberOfColumns)

/**
 * See [`content`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/content).
 */
fun attrContent(content: String) = attr("content", content)

enum class ContenteditableSetting {
    FALSE,
    PLAINTEXT_ONLY,
    TRUE,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`contenteditable`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/contenteditable).
 */
fun attrContenteditable(setting: ContenteditableSetting) = attr("contenteditable", setting.value)

/**
 * See [`contenteditable`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/contenteditable).
 */
fun attrContenteditable(setting: String) = attr("contenteditable", setting)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrControls() = attr("controls", "")

/**
 * A rectangle.
 *
 * See [`coords`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/area#coords).
 */
fun attrCoords(x1: Int, y1: Int, x2: Int, y2: Int) = attr("coords", "$x1,$y1,$x2,$y2")

/**
 * A circle.
 *
 * See [`coords`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/area#coords).
 */
fun attrCoords(x: Int, y: Int, radius: Int) = attr("coords", "$x,$y,$radius")

/**
 * A polygon. An even number of coordinates must be used.
 *
 * See [`coords`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/area#coords).
 */
fun attrCoords(vararg coordinates: Int) = attr("coords", coordinates.joinToString())

/**
 * See [`coords`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/area#coords).
 */
fun attrCoords(coordinates: String) = attr("coords", coordinates)

enum class CrossoriginState {
    ANONYMOUS,
    USE_CREDENTIALS,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`crossorigin`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/crossorigin).
 */
fun attrCrossorigin(state: CrossoriginState) = attr("crossorigin", state.value)

/**
 * See [`crossorigin`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/crossorigin).
 */
fun attrCrossorigin(state: String) = attr("crossorigin", state)

/**
 * See [`csp`](https://developer.mozilla.org/en-US/docs/Web/API/HTMLIFrameElement/csp).
 */
fun attrCsp(contentSecurityPolicies: String) = attr("csp", contentSecurityPolicies)

/**
 * See [`data`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/object#data).
 */
fun attrData(url: URL) = attr("data", url.toString())

/**
 * See [`data`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/object#data).
 */
fun attrData(url: String) = attr("data", url)

/**
 * See [`data-*`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/data-*).
 */
fun attrData(name: String, value: String) = attr("data-$name", value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrDatetime(value: String) = attr("datetime", value)

enum class DecodingHint {
    ASYNC,
    AUTO,
    SYNC,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`decoding`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/img#decoding).
 */
fun attrDecoding(hint: DecodingHint) = attr("decoding", hint.value)

/**
 * See [`decoding`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/img#decoding).
 */
fun attrDecoding(hint: String) = attr("decoding", hint)

/**
 * See [`default`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/track#default).
 */
fun attrDefault() = attr("default", "")

/**
 * See [`defer`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/script#defer).
 */
fun attrDefer() = attr("defer", "")

enum class Direction {
    AUTO,
    LTR,
    RTL,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`dir`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/dir).
 */
fun attrDir(direction: Direction) = attr("dir", direction.value)

/**
 * See [`dir`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/dir).
 */
fun attrDir(direction: String) = attr("dir", direction)

/**
 * See [`dirname`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/dirname).
 */
fun attrDirname(fieldName: String) = attr("dirname", fieldName)

/**
 * See [`disabled`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/disabled).
 */
fun attrDisabled() = attr("disabled", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrDownload(filename: String) = attr("download", filename)

/**
 * See [`draggable`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/draggable).
 */
fun attrDraggable(isDraggable: Boolean) = attr("draggable", isDraggable)

/**
 * See [`draggable`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/draggable).
 */
fun attrDraggable(isDraggable: String) = attr("draggable", isDraggable)

enum class FormEncoding(val value: String) {
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_PLAIN("text/plain");
}

/**
 * See [`enctype`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#enctype).
 */
fun attrEnctype(encoding: FormEncoding) = attr("enctype", encoding.value)

/**
 * See [`enctype`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#enctype).
 */
fun attrEnctype(encoding: String) = attr("enctype", encoding)

enum class EnterKeyHint {
    DONE,
    ENTER,
    GO,
    NEXT,
    PREVIOUS,
    SEARCH,
    SEND,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`enterkeyhint`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/enterkeyhint).
 */
fun attrEnterkeyhint(hint: EnterKeyHint) = attr("enterkeyhint", hint.value)

/**
 * See [`enterkeyhint`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/enterkeyhint).
 */
fun attrEnterkeyhint(hint: String) = attr("enterkeyhint", hint)

/**
 * See [`elementtiming`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/elementtiming).
 */
fun attrElementtiming(identifier: String) = attr("elementtiming", identifier)

/**
 * See [`for`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/for).
 */
fun attrFor(id: String) = attr("for", id)

/**
 * See [`form`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/form).
 */
fun attrForm(id: String) = attr("form", id)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormaction(url: URL) = attr("formaction", url.toString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormaction(url: String) = attr("formaction", url)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormenctype(encoding: FormEncoding) = attr("formenctype", encoding.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormenctype(encoding: String) = attr("formenctype", encoding)

enum class SubmitMethod() {
    DIALOG,
    GET,
    POST,
    ;
    
    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormmethod(method: SubmitMethod) = attr("formmethod", method.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormmethod(method: String) = attr("formmethod", method)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormnovalidate() = attr("formnovalidate", "")

enum class NavigableTarget {
    BLANK,
    PARENT,
    SELF,
    TOP,
    ;

    val value = '_' + name.lowercase(Locale.ENGLISH)
}

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormtarget(target: NavigableTarget) = attr("formtarget", target.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrFormtarget(name: String) = attr("formtarget", name)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHeaders(ids: String) = attr("headers", ids)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHeight(heightInPixels: Int) = attr("height", heightInPixels)

enum class HiddenState {
    HIDDEN,
    UNTIL_FOUND,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`hidden`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/hidden).
 */
fun attrHidden(state: HiddenState) = attr("hidden", state.value)

/**
 * See [`hidden`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/hidden).
 */
fun attrHidden(state: String) = attr("hidden", state)

/**
 * See [`high`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meter#high).
 */
fun attrHigh(highValue: Number) = attr("high", highValue.toString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHref(url: URL) = attr("href", url.toString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHref(url: String) = attr("href", url)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHreflang(locale: Locale) = attr("hreflang", locale.toLanguageTag())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrHreflang(languageTag: String) = attr("hreflang", languageTag)

enum class HttpHeader {
    CONTENT_LANGUAGE,
    CONTENT_TYPE,
    CONTENT_SECURITY_POLICY,
    DEFAULT_STYLE,
    REFRESH,
    SET_COOKIE,
    X_UA_COMPATIBLE,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See[`http-equiv`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meta/http-equiv).
 */
fun attrHttpEquiv(header: HttpHeader) = attr("http-equiv", header.value)

/**
 * See[`http-equiv`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meta/http-equiv).
 */
fun attrHttpEquiv(header: String) = attr("http-equiv", header)

/**
 * See[`id`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/id).
 */
fun attrId(id: String) = attr("id", id)

/**
 * See[`integrity`](https://developer.mozilla.org/en-US/docs/Web/Security/Defenses/Subresource_Integrity).
 */
fun attrIntegrity(algorithmPrefixedBase64Hashes: String) = attr("integrity", algorithmPrefixedBase64Hashes)

enum class InputMode {
    DECIMAL,
    EMAIL,
    NONE,
    NUMERIC,
    SEARCH,
    TEL,
    TEXT,
    URL,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See[`inputmode`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/inputmode).
 */
fun attrInputmode(inputMode: InputMode) = attr("inputmode", inputMode.value)

/**
 * See[`inputmode`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/inputmode).
 */
fun attrInputmode(inputMode: String) = attr("inputmode", inputMode)

/**
 * See[`ismap`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/img#ismap).
 */
fun attrIsmap() = attr("ismap", "")

/**
 * See[`itemprop`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/itemprop).
 */
fun attrItemprop(vararg tokens: String) = attr("itemprop", tokens.joinToString(" "))

/**
 * See[`itemprop`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/itemprop).
 * 
 * Use for both a single token and pre-joined (using spaces) tokens.
 */
fun attrItemprop(tokens: String) = attr("itemprop", tokens)

enum class TrackKind {
    CAPTIONS,
    CHAPTERS,
    DESCRIPTIONS,
    METADATA,
    SUBTITLES,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See[`kind`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/track#kind).
 */
fun attrKind(kind: TrackKind) = attr("kind", kind.value)

/**
 * See[`kind`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/track#kind).
 */
fun attrKind(kind: String) = attr("kind", kind)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrLabel(label: String) = attr("label", label)

/**
 * See [`lang`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/lang).
 */
fun attrLang(locale: Locale) = attr("lang", locale.toLanguageTag())

/**
 * See [`lang`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/lang).
 */
fun attrLang(languageTag: String) = attr("lang", languageTag)

enum class LoadingMode {
    EAGER,
    LAZY,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrLoading(mode: LoadingMode) = attr("loading", mode.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrLoading(mode: String) = attr("loading", mode)

/**
 * See [`list`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input#list).
 */
fun attrList(id: String) = attr("list", id)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrLoop() = attr("loop", "")

/**
 * See [`low`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meter#low).
 */
fun attrLow(lowValue: Number) = attr("low", lowValue.toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(maximumValue: LocalDate) = attr("max", maximumValue.toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(maximumValue: LocalDateTime) = attr("max", maximumValue.truncatedTo(MINUTES).toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(maximumValue: LocalTime) = attr("max", maximumValue.truncatedTo(MINUTES).toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(maximumValue: Number) = attr("max", maximumValue.toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(maximumValue: YearMonth) = attr("max", maximumValue.toString())

/**
 * See [`max`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/max).
 */
fun attrMax(year: Int, week: Int) = attr("max", "$year-W$week")

/**
 * See [`maxlength`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/maxlength).
 */
fun attrMaxlength(maximumLength: Int) = attr("maxlength", maximumLength)

/**
 * See [`minlength`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/minlength).
 */
fun attrMinlength(minimumLength: Int) = attr("minlength", minimumLength)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrMedia(mediaQuery: String) = attr("media", mediaQuery)

/**
 * See [`method`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#method).
 */
fun attMethod(method: SubmitMethod) = attr("method", method.value)

/**
 * See [`method`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#method).
 */
fun attMethod(method: String) = attr("method", method)

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(date: LocalDate) = attr("min", date.toString())

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(dateTime: LocalDateTime) = attr("min", dateTime.truncatedTo(MINUTES).toString())

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(time: LocalTime) = attr("min", time.truncatedTo(MINUTES).toString())

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(minimumValue: Number) = attr("min", minimumValue.toString())

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(minimumValue: YearMonth) = attr("min", minimumValue.toString())

/**
 * See [`min`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/min).
 */
fun attrMin(year: Int, week: Int) = attr("min", "$year-W$week")

/**
 * See [`multiple`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/multiple).
 */
fun attrMultiuple() = attr("multiple", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrMuted() = attr("muted", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrName(name: String) = attr("name", name)

/**
 * See [`novalidate`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/form#novalidate).
 */
fun attrNovalidate() = attr("novalidate", "")

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrOpen() = attr("open", "")

/**
 * See [`optimum`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/meter#optimum).
 */
fun attrOptimum(optimumValue: Number) = attr("optimum", optimumValue.toString())

/**
 * See [`pattern`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/pattern).
 */
fun attrPattern(pattern: Pattern) = attr("pattern", pattern.pattern())

/**
 * See [`pattern`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/pattern).
 */
fun attrPattern(pattern: String) = attr("pattern", pattern)

/**
 * See [`ping`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/a#ping).
 */
fun attrPing(url: URL) = attr("ping", url.toString())

/**
 * See [`ping`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/a#ping).
 */
fun attrPing(vararg urls: URL) = attr("ping", urls.joinToString(" "))

/**
 * See [`ping`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/a#ping).
 */
fun attrPing(urls: String) = attr("ping", urls)

/**
 * See [`placeholder`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/placeholder).
 */
fun attrPlaceholder(placeholder: String) = attr("placeholder", placeholder)

/**
 * See [`playsinline`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/video#playsinline).
 */
fun attrPlaysinline() = attr("playsinline", "")

/**
 * See [`poster`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/video#poster).
 */
fun attrPoster(url: URL) = attr("poster", url.toString())

/**
 * See [`poster`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/video#poster).
 */
fun attrPoster(url: String) = attr("poster", url)

enum class DataToPreload {
    AUTO,
    METADATA,
    NONE,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrPreload(data: DataToPreload) = attr("preload", data.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrPreload(data: String) = attr("preload", data)

/**
 * See [`readonly`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/readonly).
 */
fun attrReadonly() = attr("readonly", "")

enum class ReferrerPolicy {
    NO_REFERRER,
    NO_REFERRER_WHEN_DOWNGRADE,
    ORIGIN,
    ORIGIN_WHEN_CROSS_ORIGIN,
    SAME_ORIGIN,
    STRICT_ORIGIN,
    STRICT_ORIGIN_WHEN_CROSS_ORIGIN,
    UNSAFE_URL,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrReferrerpolicy(policy: ReferrerPolicy) = attr("referrerpolicy", policy.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrReferrerpolicy(policy: String) = attr("referrerpolicy", policy)

enum class LinkedResourceRelationship {
    ALTERNATE,
    AUTHOR,
    BOOKMARK,
    CANONICAL,
    COMPRESSION_DICTIONARY,
    DNS_PREFETCH,
    EXPECT,
    EXTERNAL,
    HELP,
    ICON,
    LICENSE,
    MANIFEST,
    ME,
    MODULEPRELOAD,
    NEXT,
    NOFOLLOW,
    NOOPENER,
    NOREFERRER,
    OPENER,
    PINGBACK,
    PRECONNECT,
    PREFETCH,
    PRELOAD,
    PREV,
    PRIVACY_POLICY,
    SEARCH,
    STYLESHEET,
    TAG,
    TERMS_OF_SERVICE,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`rel`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/rel).
 */
fun attrRel(relationship: LinkedResourceRelationship) = attr("rel", relationship.value)

/**
 * See [`rel`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/rel).
 */
fun attrRel(relationship: String) = attr("rel", relationship)

/**
 * See [`required`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/required).
 */
fun attrRequired() = attr("required", "")

/**
 * See [`reversed`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/ol#reversed).
 */
fun attrReversed() = attr("reversed", "")

enum class AriaRole {
    ALERT,
    ALERTDIALOG,
    ASSOCIATIONLIST,
    ASSOCIATIONLISTITEMKEY,
    ASSOCIATIONLISTITEMVALUE,
    BANNER,
    BLOCKQUOTE,
    CAPTION,
    CODE,
    COMBOBOX,
    COMPLEMENTARY,
    CONTENTINFO,
    DELETION,
    DIALOG,
    EMPHASIS,
    FEED,
    FORM,
    INSERTION,
    LOG,
    MAIN,
    MARQUEE,
    MATH,
    MENU,
    MENUBAR,
    NAVIGATION,
    NOTE,
    PARAGRAPH,
    PRESENTATION,
    REGION,
    SCROLLBAR,
    SEARCH,
    SEARCHBOX,
    SEPARATOR,
    SLIDER,
    SPINBUTTON,
    STATUS,
    STRONG,
    SUBSCRIPT,
    SUPERSCRIPT,
    SWITCH,
    TAB,
    TABLIST,
    TABPANEL,
    TIME,
    TIMER,
    TOOLBAR,
    TOOLTIP,
    TREE,
    TREEGRID,
    TREEITEM,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

enum class AriaRoleToAvoid {
    APPLICATION,
    ARTICLE,
    BUTTON,
    CELL,
    CHECKBOX,
    COLUMNHEADER,
    DEFINITION,
    DIRECTORY,
    DOCUMENT,
    FIGURE,
    GRID,
    GRIDCELL,
    GROUP,
    HEADING,
    IMG,
    LINK,
    LIST,
    LISTBOX,
    LISTITEM,
    MENUITEM,
    MENUITEMCHECKBOX,
    MENUITEMRADIO,
    METER,
    OPTION,
    PROGRESSBAR,
    RADIO,
    RADIOGROUP,
    ROW,
    ROWGROUP,
    ROWHEADER,
    TABLE,
    TERM,
    TEXTBOX,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [*WAI-ARIA Roles*](https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/Reference/Roles).
 */
fun attrRole(role: AriaRole) = attr("role", role.value)

/**
 * See [*WAI-ARIA Roles*](https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/Reference/Roles).
 */
fun attrRole(role: AriaRoleToAvoid) = attr("role", role.value)

/**
 * See [*WAI-ARIA Roles*](https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/Reference/Roles).
 */
fun attrRole(role: String) = attr("role", role)

/**
 * See [`rows`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/textarea#rows).
 */
fun attrRows(numberOfRows: Int) = attr("rows", numberOfRows)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrRowspan(numberOfRows: Int) = attr("rowspan", numberOfRows)

enum class IframeContentRestriction {
    ALLOW_DOWNLOADS,
    ALLOW_FORMS,
    ALLOW_MODALS,
    ALLOW_ORIENTATION_LOCK,
    ALLOW_POINTER_LOCK,
    ALLOW_POPUPS,
    ALLOW_POPUPS_TO_ESCAPE_SANDBOX,
    ALLOW_PRESENTATION,
    ALLOW_SAME_ORIGIN,
    ALLOW_SCRIPTS,
    ALLOW_STORAGE_ACCESS_BY_USER_ACTIVATION,
    ALLOW_TOP_NAVIGATION,
    ALLOW_TOP_NAVIGATION_BY_USER_ACTIVATION,
    ALLOW_TOP_NAVIGATION_TO_CUSTOM_PROTOCOLS,
    ;
    
    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`sandbox`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/iframe#sandbox).
 */
fun attrSandbox(vararg restrictions: IframeContentRestriction) = attr("sandbox", restrictions.joinToString(" ") { it.value })

/**
 * See [`sandbox`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/iframe#sandbox).
 */
fun attrSandbox(restrictions: String) = attr("sandbox", restrictions)

enum class TableHeaderScope {
    COL,
    COLGROUP,
    ROW,
    ROWGROUP,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`scope`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/th#scope).
 */
fun attrScope(scope: TableHeaderScope) = attr("scope", scope.value)

/**
 * See [`scope`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/th#scope).
 */
fun attrScope(scope: String) = attr("scope", scope)

/**
 * See [`selected`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/option#selected).
 */
fun attrSelected() = attr("selected", "")

enum class AreaShape {
    CIRCLE,
    DEFAULT,
    POLY,
    RECT,
    ;

    val value = name.lowercase(Locale.ENGLISH)
} 

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrShape(shape: AreaShape) = attr("shape", shape.value)
 
/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrShape(shape: String) = attr("shape", shape)

/**
 * See [`size`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/size).
 */
fun attrSize(size: Int) = attr("size", size)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSize(sizes: String) = attr("sizes", sizes)

/**
 * See [`slot`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/slot).
 */
fun attrSlot(name: String) = attr("slot", name)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSpen(numberOfColumns: Int) = attr("span", numberOfColumns)

/**
 * See [`spellcheck`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/spellcheck).
 */
fun attrSpellcheck(shouldBeCheckedForSpellingErrors: Boolean) = attr("spellcheck", shouldBeCheckedForSpellingErrors)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSrc(uri: URI) = attr("src", uri.toASCIIString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSrc(url: URL) = attr("src", url.toString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSrc(uriOrUrl: String) = attr("src", uriOrUrl)

/**
 * See [`srcdoc`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/iframe#srcdoc).
 */
fun attrSrcdoc(html: String) = attr("srcdoc", html)

/**
 * See [`srclang`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/track#srclang).
 */
fun attrSrclang(locale: Locale) = attr("srclang", locale.toLanguageTag())

/**
 * See [`srclang`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/track#srclang).
 */
fun attrSrclang(languageTag: String) = attr("srclang", languageTag)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrSrcset(vararg urlsWithDescriptors: String) = attr("srcset", urlsWithDescriptors.joinToString())

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 * 
 * Use for both a single url-with-descriptors and pre-joined (using commas) urls-with-descriptors.
 */
fun attrSrcset(urlsWithDescriptors: String) = attr("srcset", urlsWithDescriptors)

/**
 * See [`start`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/ol#start).
 */
fun attrStart(startNumeral: Int) = attr("start", startNumeral)

/**
 * See [`step`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/step).
 */
fun attrStep(stepSize: Number) = attr("step", stepSize.toString())

/**
 * See [`step`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/step).
 */
fun attrStepAny() = attr("step", "any")

/**
 * See [`style`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/style).
 */
fun attrStyle(stylingDeclarations: Properties) = attr(
    "style",
    stylingDeclarations.entries.fold(StringBuilder(stylingDeclarations.size * 25)) { builder, entry ->
        builder.append(entry.key).append(':').append(entry.value).append(';')
    }.toString()
)

/**
 * See [`style`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/style).
 */
fun attrStyle(stylingDeclarations: String) = attr("style", stylingDeclarations)

sealed interface Tabindex {
    val value: Int
}

object NotReachable : Tabindex {
    override val value = -1
}


object DocumentOrder : Tabindex {
    override val value = 0
}

class SpecificOrder(override val value: Int) : Tabindex {
    init {
        require(value in 1..32767) { "Value must be greater than 0 and less than or equal to 32767." }
    }
}

/**
 * See [`tabindex`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/tabindex).
 */
fun attrTabindex(tabindex: Tabindex) = attr("tabindex", tabindex.value)

/**
 * See [`tabindex`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/tabindex).
 */
fun attrTabindex(tabindex: String) = attr("tabindex", tabindex)

sealed interface Target {
    val value: String
}

object Self : Target {
    override val value = "_self"
}

object Blank : Target {
    override val value = "_blank"
}

object Parent : Target {
    override val value = "_parent"
}

object Top : Target {
    override val value = "_top"
}

object UnfencedTop : Target {
    override val value = "_unfencedTop"
}

class NamedBrowsingContext(override val value: String) : Target

/**
 * See [`target`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrTarget(target: Target) = attr("target", target.value)

/**
 * See [`target`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrTarget(target: String) = attr("target", target)

/**
 * See [`title`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/title.)
 */
fun attrTitle(title: String) = attr("title", title)

/**
 * See [`translate`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/translate).
 */
fun attrTranslate(canBeTranslated: Boolean) = attr("translate", if (canBeTranslated) "yes" else "no")

/**
 * See [`translate`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Global_attributes/translate).
 */
fun attrTranslate(canBeTranslated: String) = attr("translate", canBeTranslated)

enum class ButtonType {
    BUTTON,
    RESET,
    SUBMIT,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`type` of `<button>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/button#type).
 */
fun attrType(type: ButtonType) = attr("type", type.value)

enum class InputType {
    BUTTON,
    CHECKBOX,
    COLOR,
    DATE,
    DATETIME_LOCAL,
    EMAIL,
    FILE,
    HIDDEN,
    IMAGE,
    MONTH,
    NUMBER,
    PASSWORD,
    RADIO,
    RANGE,
    RESET,
    SEARCH,
    SUBMIT,
    TEL,
    TEXT,
    TIME,
    URL,
    WEEK,
    ;

    val value = name.lowercase(Locale.ENGLISH).replace('_', '-')
}

/**
 * See [`type` of `<input>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input#input_types).
 */
fun attrType(type: InputType) = attr("type", type.value)

enum class OrderedListType(val value: String) {
    LOWERCASE_LETTERS("a"),
    LOWERCASE_ROMAN_NUMERALS("i"),
    NUMBERS("1"),
    UPPERCASE_LETTERS("A"),
    UPPERCASE_ROMAN_NUMERALS("I"),
    ;
}

/**
 * See [`type` of `<ol>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/ol#type).
 */
fun attrType(type: OrderedListType) = attr("type", type.value)

enum class ScriptType(val value: String) {
    IMPORTMAP("importmap"),
    JAVASCRIPT("text/javascript"),
    MODULE("module"),
    SPECULATIONRULES("speculationrules"),
    ;
}

/**
 * See [`type` of `<script>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/script/type).
 */
fun attrType(type: ScriptType) = attr("type", type.value)

enum class MenuType {
    CONTEXT,
    TOOLBAR,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`type` of `<menu>`](https://www.w3.org/TR/2011/WD-html5-author-20110809/the-menu-element.html#list-state).
 */
fun attrType(type: MenuType) = attr("type", type.value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrType(type: String) = attr("type", type)

/**
 * See [`usemap` of `<img>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/img#usemap).
 *
 * The prefix `#` is not added automatically.
 */
fun attrUsemap(partialUrl: String) = attr("usemap", partialUrl)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrValue(value: Int) = attr("value", value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrValue(value: String) = attr("value", value)

/**
 * See [*HTML attribute refence*](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes).
 */
fun attrWidth(widthInPixels: Int) = attr("width", widthInPixels)

enum class WrapMode {
    HARD,
    OFF,
    SOFT,
    ;

    val value = name.lowercase(Locale.ENGLISH)
}

/**
 * See [`wrap` of `<textarea>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/textarea#wrap).
 */
fun attrWrap(mode: WrapMode) = attr("wrap", mode.value)

/**
 * See [`wrap` of `<textarea>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/textarea#wrap).
 */
fun attrWrap(mode: String) = attr("wrap", mode)
