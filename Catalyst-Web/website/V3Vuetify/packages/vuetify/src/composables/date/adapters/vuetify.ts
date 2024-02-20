// Utilities
import { createRange, padStart } from '@/util'

// Types
import type { DateAdapter } from '../DateAdapter'

type CustomDateFormat = Intl.DateTimeFormatOptions | ((date: Date, formatString: string, locale: string) => string)

const firstDay: Record<string, number> = {
  '001': 1,
  AD: 1,
  AE: 6,
  AF: 6,
  AG: 0,
  AI: 1,
  AL: 1,
  AM: 1,
  AN: 1,
  AR: 1,
  AS: 0,
  AT: 1,
  AU: 1,
  AX: 1,
  AZ: 1,
  BA: 1,
  BD: 0,
  BE: 1,
  BG: 1,
  BH: 6,
  BM: 1,
  BN: 1,
  BR: 0,
  BS: 0,
  BT: 0,
  BW: 0,
  BY: 1,
  BZ: 0,
  CA: 0,
  CH: 1,
  CL: 1,
  CM: 1,
  CN: 1,
  CO: 0,
  CR: 1,
  CY: 1,
  CZ: 1,
  DE: 1,
  DJ: 6,
  DK: 1,
  DM: 0,
  DO: 0,
  DZ: 6,
  EC: 1,
  EE: 1,
  EG: 6,
  ES: 1,
  ET: 0,
  FI: 1,
  FJ: 1,
  FO: 1,
  FR: 1,
  GB: 1,
  'GB-alt-variant': 0,
  GE: 1,
  GF: 1,
  GP: 1,
  GR: 1,
  GT: 0,
  GU: 0,
  HK: 0,
  HN: 0,
  HR: 1,
  HU: 1,
  ID: 0,
  IE: 1,
  IL: 0,
  IN: 0,
  IQ: 6,
  IR: 6,
  IS: 1,
  IT: 1,
  JM: 0,
  JO: 6,
  JP: 0,
  KE: 0,
  KG: 1,
  KH: 0,
  KR: 0,
  KW: 6,
  KZ: 1,
  LA: 0,
  LB: 1,
  LI: 1,
  LK: 1,
  LT: 1,
  LU: 1,
  LV: 1,
  LY: 6,
  MC: 1,
  MD: 1,
  ME: 1,
  MH: 0,
  MK: 1,
  MM: 0,
  MN: 1,
  MO: 0,
  MQ: 1,
  MT: 0,
  MV: 5,
  MX: 0,
  MY: 1,
  MZ: 0,
  NI: 0,
  NL: 1,
  NO: 1,
  NP: 0,
  NZ: 1,
  OM: 6,
  PA: 0,
  PE: 0,
  PH: 0,
  PK: 0,
  PL: 1,
  PR: 0,
  PT: 0,
  PY: 0,
  QA: 6,
  RE: 1,
  RO: 1,
  RS: 1,
  RU: 1,
  SA: 0,
  SD: 6,
  SE: 1,
  SG: 0,
  SI: 1,
  SK: 1,
  SM: 1,
  SV: 0,
  SY: 6,
  TH: 0,
  TJ: 1,
  TM: 1,
  TR: 1,
  TT: 0,
  TW: 0,
  UA: 1,
  UM: 0,
  US: 0,
  UY: 1,
  UZ: 1,
  VA: 1,
  VE: 0,
  VI: 0,
  VN: 1,
  WS: 0,
  XK: 1,
  YE: 0,
  ZA: 0,
  ZW: 0,
}

function getWeekArray (date: Date, locale: string) {
  const weeks = []
  let currentWeek = []
  const firstDayOfMonth = startOfMonth(date)
  const lastDayOfMonth = endOfMonth(date)
  const firstDayWeekIndex = (firstDayOfMonth.getDay() - firstDay[locale.slice(-2).toUpperCase()] + 7) % 7
  const lastDayWeekIndex = (lastDayOfMonth.getDay() - firstDay[locale.slice(-2).toUpperCase()] + 7) % 7

  for (let i = 0; i < firstDayWeekIndex; i++) {
    const adjacentDay = new Date(firstDayOfMonth)
    adjacentDay.setDate(adjacentDay.getDate() - (firstDayWeekIndex - i))
    currentWeek.push(adjacentDay)
  }

  for (let i = 1; i <= lastDayOfMonth.getDate(); i++) {
    const day = new Date(date.getFullYear(), date.getMonth(), i)

    // Add the day to the current week
    currentWeek.push(day)

    // If the current week has 7 days, add it to the weeks array and start a new week
    if (currentWeek.length === 7) {
      weeks.push(currentWeek)
      currentWeek = []
    }
  }

  for (let i = 1; i < 7 - lastDayWeekIndex; i++) {
    const adjacentDay = new Date(lastDayOfMonth)
    adjacentDay.setDate(adjacentDay.getDate() + i)
    currentWeek.push(adjacentDay)
  }

  if (currentWeek.length > 0) {
    weeks.push(currentWeek)
  }

  return weeks
}

function startOfWeek (date: Date) {
  const d = new Date(date)
  while (d.getDay() !== 0) {
    d.setDate(d.getDate() - 1)
  }
  return d
}

function endOfWeek (date: Date) {
  const d = new Date(date)
  while (d.getDay() !== 6) {
    d.setDate(d.getDate() + 1)
  }
  return d
}

function startOfMonth (date: Date) {
  return new Date(date.getFullYear(), date.getMonth(), 1)
}

function endOfMonth (date: Date) {
  return new Date(date.getFullYear(), date.getMonth() + 1, 0)
}

function parseLocalDate (value: string): Date {
  const parts = value.split('-').map(Number)

  // new Date() uses local time zone when passing individual date component values
  return new Date(parts[0], parts[1] - 1, parts[2])
}

const _YYYMMDD = /^([12]\d{3}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[12]\d|3[01]))$/

function date (value?: any): Date | null {
  if (value == null) return new Date()

  if (value instanceof Date) return value

  if (typeof value === 'string') {
    let parsed

    if (_YYYMMDD.test(value)) {
      return parseLocalDate(value)
    } else {
      parsed = Date.parse(value)
    }

    if (!isNaN(parsed)) return new Date(parsed)
  }

  return null
}

const sundayJanuarySecond2000 = new Date(2000, 0, 2)

function getWeekdays (locale: string) {
  const daysFromSunday = firstDay[locale.slice(-2).toUpperCase()]

  return createRange(7).map(i => {
    const weekday = new Date(sundayJanuarySecond2000)
    weekday.setDate(sundayJanuarySecond2000.getDate() + daysFromSunday + i)
    return new Intl.DateTimeFormat(locale, { weekday: 'narrow' }).format(weekday)
  })
}

function format (
  value: Date,
  formatString: string,
  locale: string,
  formats?: Record<string, CustomDateFormat>
): string {
  const newDate = date(value) ?? new Date()
  const customFormat = formats?.[formatString]

  if (typeof customFormat === 'function') {
    return customFormat(newDate, formatString, locale)
  }

  let options: Intl.DateTimeFormatOptions = {}
  switch (formatString) {
    case 'fullDateWithWeekday':
      options = { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' }
      break
    case 'hours12h':
      options = { hour: 'numeric', hour12: true }
      break
    case 'normalDateWithWeekday':
      options = { weekday: 'short', day: 'numeric', month: 'short' }
      break
    case 'keyboardDate':
      options = { day: '2-digit', month: '2-digit', year: 'numeric' }
      break
    case 'monthAndDate':
      options = { month: 'long', day: 'numeric' }
      break
    case 'monthAndYear':
      options = { month: 'long', year: 'numeric' }
      break
    case 'month':
      options = { month: 'long' }
      break
    case 'monthShort':
      options = { month: 'short' }
      break
    case 'dayOfMonth':
      return new Intl.NumberFormat(locale).format(newDate.getDate())
    case 'shortDate':
      options = { year: '2-digit', month: 'numeric', day: 'numeric' }
      break
    case 'weekdayShort':
      options = { weekday: 'short' }
      break
    case 'year':
      options = { year: 'numeric' }
      break
    default:
      options = customFormat ?? { timeZone: 'UTC', timeZoneName: 'short' }
  }

  return new Intl.DateTimeFormat(locale, options).format(newDate)
}

function toISO (adapter: DateAdapter<any>, value: Date) {
  const date = adapter.toJsDate(value)
  const year = date.getFullYear()
  const month = padStart(String(date.getMonth() + 1), 2, '0')
  const day = padStart(String(date.getDate()), 2, '0')

  return `${year}-${month}-${day}`
}

function parseISO (value: string) {
  const [year, month, day] = value.split('-').map(Number)

  return new Date(year, month - 1, day)
}

function addMinutes (date: Date, amount: number) {
  const d = new Date(date)
  d.setMinutes(d.getMinutes() + amount)
  return d
}

function addHours (date: Date, amount: number) {
  const d = new Date(date)
  d.setHours(d.getHours() + amount)
  return d
}

function addDays (date: Date, amount: number) {
  const d = new Date(date)
  d.setDate(d.getDate() + amount)
  return d
}

function addWeeks (date: Date, amount: number) {
  const d = new Date(date)
  d.setDate(d.getDate() + (amount * 7))
  return d
}

function addMonths (date: Date, amount: number) {
  const d = new Date(date)
  d.setMonth(d.getMonth() + amount)
  return d
}

function getYear (date: Date) {
  return date.getFullYear()
}

function getMonth (date: Date) {
  return date.getMonth()
}

function getNextMonth (date: Date) {
  return new Date(date.getFullYear(), date.getMonth() + 1, 1)
}

function getHours (date: Date) {
  return date.getHours()
}

function getMinutes (date: Date) {
  return date.getMinutes()
}

function startOfYear (date: Date) {
  return new Date(date.getFullYear(), 0, 1)
}
function endOfYear (date: Date) {
  return new Date(date.getFullYear(), 11, 31)
}

function isWithinRange (date: Date, range: [Date, Date]) {
  return isAfter(date, range[0]) && isBefore(date, range[1])
}

function isValid (date: any) {
  const d = new Date(date)

  return d instanceof Date && !isNaN(d.getTime())
}

function isAfter (date: Date, comparing: Date) {
  return date.getTime() > comparing.getTime()
}

function isBefore (date: Date, comparing: Date) {
  return date.getTime() < comparing.getTime()
}

function isEqual (date: Date, comparing: Date) {
  return date.getTime() === comparing.getTime()
}

function isSameDay (date: Date, comparing: Date) {
  return date.getDate() === comparing.getDate() &&
    date.getMonth() === comparing.getMonth() &&
    date.getFullYear() === comparing.getFullYear()
}

function isSameMonth (date: Date, comparing: Date) {
  return date.getMonth() === comparing.getMonth() &&
    date.getFullYear() === comparing.getFullYear()
}

function getDiff (date: Date, comparing: Date | string, unit?: string) {
  const d = new Date(date)
  const c = new Date(comparing)

  if (unit === 'month') {
    return d.getMonth() - c.getMonth() + (d.getFullYear() - c.getFullYear()) * 12
  }

  return Math.floor((d.getTime() - c.getTime()) / (1000 * 60 * 60 * 24))
}

function setHours (date: Date, count: number) {
  const d = new Date(date)
  d.setHours(count)
  return d
}

function setMinutes (date: Date, count: number) {
  const d = new Date(date)
  d.setMinutes(count)
  return d
}

function setMonth (date: Date, count: number) {
  const d = new Date(date)
  d.setMonth(count)
  return d
}

function setYear (date: Date, year: number) {
  const d = new Date(date)
  d.setFullYear(year)
  return d
}

function startOfDay (date: Date) {
  return new Date(date.getFullYear(), date.getMonth(), date.getDate())
}

function endOfDay (date: Date) {
  return new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59, 999)
}

export class VuetifyDateAdapter implements DateAdapter<Date> {
  locale: string
  formats?: Record<string, CustomDateFormat>

  constructor (options: { locale: string, formats?: Record<string, CustomDateFormat> }) {
    this.locale = options.locale
    this.formats = options.formats
  }

  date (value?: any) {
    return date(value)
  }

  toJsDate (date: Date) {
    return date
  }

  toISO (date: Date): string {
    return toISO(this, date)
  }

  parseISO (date: string) {
    return parseISO(date)
  }

  addMinutes (date: Date, amount: number) {
    return addMinutes(date, amount)
  }

  addHours (date: Date, amount: number) {
    return addHours(date, amount)
  }

  addDays (date: Date, amount: number) {
    return addDays(date, amount)
  }

  addWeeks (date: Date, amount: number) {
    return addWeeks(date, amount)
  }

  addMonths (date: Date, amount: number) {
    return addMonths(date, amount)
  }

  getWeekArray (date: Date) {
    return getWeekArray(date, this.locale)
  }

  startOfWeek (date: Date): Date {
    return startOfWeek(date)
  }

  endOfWeek (date: Date): Date {
    return endOfWeek(date)
  }

  startOfMonth (date: Date) {
    return startOfMonth(date)
  }

  endOfMonth (date: Date) {
    return endOfMonth(date)
  }

  format (date: Date, formatString: string) {
    return format(date, formatString, this.locale, this.formats)
  }

  isEqual (date: Date, comparing: Date) {
    return isEqual(date, comparing)
  }

  isValid (date: any) {
    return isValid(date)
  }

  isWithinRange (date: Date, range: [Date, Date]) {
    return isWithinRange(date, range)
  }

  isAfter (date: Date, comparing: Date) {
    return isAfter(date, comparing)
  }

  isBefore (date: Date, comparing: Date) {
    return !isAfter(date, comparing) && !isEqual(date, comparing)
  }

  isSameDay (date: Date, comparing: Date) {
    return isSameDay(date, comparing)
  }

  isSameMonth (date: Date, comparing: Date) {
    return isSameMonth(date, comparing)
  }

  setMinutes (date: Date, count: number) {
    return setMinutes(date, count)
  }

  setHours (date: Date, count: number) {
    return setHours(date, count)
  }

  setMonth (date: Date, count: number) {
    return setMonth(date, count)
  }

  setYear (date: Date, year: number) {
    return setYear(date, year)
  }

  getDiff (date: Date, comparing: Date | string, unit?: string) {
    return getDiff(date, comparing, unit)
  }

  getWeekdays () {
    return getWeekdays(this.locale)
  }

  getYear (date: Date) {
    return getYear(date)
  }

  getMonth (date: Date) {
    return getMonth(date)
  }

  getNextMonth (date: Date) {
    return getNextMonth(date)
  }

  getHours (date: Date) {
    return getHours(date)
  }

  getMinutes (date: Date) {
    return getMinutes(date)
  }

  startOfDay (date: Date) {
    return startOfDay(date)
  }

  endOfDay (date: Date) {
    return endOfDay(date)
  }

  startOfYear (date: Date) {
    return startOfYear(date)
  }

  endOfYear (date: Date) {
    return endOfYear(date)
  }
}
