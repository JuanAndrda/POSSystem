package com.mycompany.possystem.ui.theme;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class AppTheme {

    // ── Color Palette ────────────────────────────────────────────────
    public static final Color BG_DARK     = new Color(0x0A1628);
    public static final Color BG_PANEL    = new Color(0x0F1E35);
    public static final Color BG_CARD     = new Color(0x162440);
    public static final Color BG_HOVER    = new Color(0x1C2E50);
    public static final Color BG_SELECTED = new Color(0x00507A);

    public static final Color ACCENT      = new Color(0x00AACC);
    public static final Color ACCENT_BTN  = new Color(0x0088AA);
    public static final Color SUCCESS     = new Color(0x00C896);
    public static final Color DANGER      = new Color(0xFF4060);
    public static final Color WARNING     = new Color(0xFFAA00);

    public static final Color TEXT_PRI    = new Color(0xF0F4F8);
    public static final Color TEXT_SEC    = new Color(0x7A9BBE);
    public static final Color BORDER      = new Color(0x1E3A5F);

    // ── Fonts ────────────────────────────────────────────────────────
    public static final Font FONT_TITLE   = new Font("Segoe UI", Font.BOLD,  24);
    public static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD,  16);
    public static final Font FONT_BODY    = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL   = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_PRICE   = new Font("Segoe UI", Font.BOLD,  20);
    public static final Font FONT_TOTAL   = new Font("Segoe UI", Font.BOLD,  32);
    public static final Font FONT_MONO    = new Font("Consolas",  Font.PLAIN, 13);
    public static final Font FONT_NAV     = new Font("Segoe UI", Font.BOLD,  14);

    // ── Spacing ──────────────────────────────────────────────────────
    public static final int PAD_SM = 8;
    public static final int PAD_MD = 16;
    public static final int PAD_LG = 24;

    // ── Rounded Border ───────────────────────────────────────────────
    /**
     * Creates a rounded border with a given radius and color.
     * Use this on JPanels to get card-style rounded corners.
     */
    public static javax.swing.border.Border roundedBorder(
            int radius, Color color) {
        return new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g,
                    int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.setStroke(new BasicStroke(1.5f));
                g2.draw(new RoundRectangle2D.Float(
                    x + 1, y + 1, width - 2, height - 2, radius, radius));
                g2.dispose();
            }
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius / 2, radius / 2,
                                  radius / 2, radius / 2);
            }
        };
    }

    // ── Rounded Panel ────────────────────────────────────────────────
    /**
     * Returns a JPanel that paints itself with rounded corners.
     * Use instead of plain JPanel for card-style sections.
     */
    public static JPanel roundedPanel(Color bg, int radius) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), radius, radius));
                g2.dispose();
            }
            @Override
            public boolean isOpaque() { return false; }
        };
        return panel;
    }

    // ── Hover Button ─────────────────────────────────────────────────
    /**
     * Adds a smooth hover color change to any JButton.
     */
    public static void addHoverEffect(JButton btn,
            Color normal, Color hover) {
        btn.setBackground(normal);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(normal);
            }
        });
    }

    // ── Style a text input field ──────────────────────────────────────
    public static void styleField(JComponent field) {
        field.setBackground(BG_CARD);
        field.setForeground(TEXT_PRI);
        field.setFont(FONT_BODY);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        if (field instanceof JTextField tf) {
            tf.setCaretColor(ACCENT);
        }
    }

    // ── Style a table completely ──────────────────────────────────────
    public static void styleTable(javax.swing.JTable table) {
        table.setBackground(BG_CARD);
        table.setForeground(TEXT_PRI);
        table.setFont(FONT_BODY);
        table.setRowHeight(38);
        table.setGridColor(BORDER);
        table.setSelectionBackground(BG_SELECTED);
        table.setSelectionForeground(TEXT_PRI);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getTableHeader().setBackground(BG_PANEL);
        table.getTableHeader().setForeground(TEXT_SEC);
        table.getTableHeader().setFont(FONT_SMALL);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER));

        // Alternating row colors
        table.setDefaultRenderer(Object.class,
            new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        javax.swing.JTable t, Object val, boolean sel,
                        boolean foc, int row, int col) {
                    super.getTableCellRendererComponent(
                        t, val, sel, foc, row, col);
                    if (!sel) {
                        setBackground(row % 2 == 0 ? BG_CARD : BG_PANEL);
                        setForeground(TEXT_PRI);
                    }
                    setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                    return this;
                }
            });
    }

    // ── Style a nav button ────────────────────────────────────────────
    public static void styleNavButton(JButton btn, String text) {
        btn.setText(text);
        btn.setBackground(BG_PANEL);
        btn.setForeground(TEXT_PRI);
        btn.setFont(FONT_NAV);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        addHoverEffect(btn, BG_PANEL, BG_HOVER);
    }

    // ── Style a primary action button ─────────────────────────────────
    public static void stylePrimaryButton(JButton btn, String text,
                                           Color bg) {
        btn.setText(text);
        btn.setBackground(bg);
        btn.setForeground(bg.equals(BG_HOVER) ? TEXT_PRI : BG_DARK);
        btn.setFont(FONT_BODY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hover: slightly brighter
        Color hover = bg.brighter();
        addHoverEffect(btn, bg, hover);
    }

    // ── Apply dark background ─────────────────────────────────────────
    public static void applyDark(JComponent c) {
        c.setBackground(BG_DARK);
        c.setForeground(TEXT_PRI);
        c.setFont(FONT_BODY);
        c.setOpaque(true);
    }
}