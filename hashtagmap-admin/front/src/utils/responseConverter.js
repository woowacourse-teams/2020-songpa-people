import { MESSAGE, SNACK_BAR_TEMPLATE } from "@/utils/constants";

export const convert = {
  toSnackBarContent: function(response) {
    if (!response) {
      return SNACK_BAR_TEMPLATE.INFO(MESSAGE.NO_CONTENT);
    }
    switch (response.status) {
      case 200:
        return SNACK_BAR_TEMPLATE.SUCCESS();
      case 201:
        return SNACK_BAR_TEMPLATE.SUCCESS();
      case 204:
        return SNACK_BAR_TEMPLATE.SUCCESS();
      case 400:
        return SNACK_BAR_TEMPLATE.ERROR(response);
      case 404:
        return SNACK_BAR_TEMPLATE.ERROR(response);
    }
  }
};
