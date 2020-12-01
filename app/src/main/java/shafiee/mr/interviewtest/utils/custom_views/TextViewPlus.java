package shafiee.mr.interviewtest.utils.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import shafiee.mr.interviewtest.R;
import shafiee.mr.interviewtest.utils.FontManager;


public class TextViewPlus extends AppCompatTextView {

    public TextViewPlus(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public TextViewPlus(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextViewPlus(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
            int font = typedArray.getInt(R.styleable.TextViewPlus_tvp_font, 1);
            setTypeface(FontManager.getTypeface(context, font));
            typedArray.recycle();
        } else {
            setTypeface(FontManager.getTypeface(context, 0));
        }
    }
}
